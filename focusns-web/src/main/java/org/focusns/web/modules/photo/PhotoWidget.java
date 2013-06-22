package org.focusns.web.modules.photo;

import org.focusns.common.web.WebUtils;
import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.model.photo.Album;
import org.focusns.model.photo.Photo;
import org.focusns.service.common.StorageService;
import org.focusns.service.common.helper.CoordinateHelper;
import org.focusns.service.photo.AlbumService;
import org.focusns.service.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Widget
@RequestMapping("photo")
public class PhotoWidget {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private AlbumService albumService;
    @Autowired@Qualifier("localStorageService")
    private StorageService storageService;

    @RequestMapping("photo-list")
    public String doList(@RequestParam Long albumId, Model model) {
        //
        Album album = albumService.getAlbum(albumId);
        model.addAttribute("album", album);
        List<Photo> photos = photoService.listPhoto(albumId);
        model.addAttribute("photos", photos);
        //
        return "modules/photo/photo-list";
    }

    @RequestMapping("photo-edit")
    public String doEdit(@RequestParam(required = false) Long albumId, @WidgetAttribute Project project,
                         @WidgetAttribute ProjectUser projectUser, Model model) {
        //
        Photo photo = new Photo();
        photo.setAlbumId(albumId==null?0:albumId);
        photo.setProjectId(project.getId());
        photo.setCreatedById(projectUser.getId());
        model.addAttribute("photo", photo);
        //
        List<Album> albums = albumService.listAlbums(project.getId());
        model.addAttribute("albums", albums);
        //
        return "modules/photo/photo-edit";
    }

    @RequestMapping("photo-remove")
    public void doRemove(Photo photo) throws IOException {
        photoService.removePhoto(photo);
        Object[] photoCoordinates = CoordinateHelper.getPhotoCoordinates(photo);
        storageService.removeResource(photoCoordinates);
        //
        Navigator.get().withAttribute("photo", photo).navigateTo("photo-removed");
    }

    @RequestMapping("photo-upload")
    public void doUpload(Photo photo, MultipartFile file) throws IOException {
        //
        if(!file.isEmpty()) {
            //
            photoService.createPhoto(photo);
            Object[] photoCoordinates = CoordinateHelper.getPhotoCoordinates(photo);
            storageService.persistResource(file.getInputStream(), photoCoordinates);
            //
            Navigator.get().withAttribute("photo", photo).navigateTo("photo-uploaded");
        }
    }

    @RequestMapping("photo-download")
    public ResponseEntity<byte[]> doDownload(@RequestParam Long photoId, @RequestParam(required = false) Integer width,
                                             @RequestParam(required = false) Integer height, WebRequest webRequest) throws IOException {
        //
        boolean notModified = false;
        InputStream inputStream = null;
        //
        Photo photo = photoService.getPhoto(photoId);
        Object[] photoCoordinates = CoordinateHelper.getPhotoCoordinates(photo);
        if(width==null || height==null) {
            long lastModified = storageService.checkResource(photoCoordinates);
            if(lastModified>0 && webRequest.checkNotModified(lastModified)) {
                notModified = true;
            } else {
                inputStream = storageService.loadResource(photoCoordinates);
            }
        } else {
            Object size = width + "x" + height;
            long lastModified = storageService.checkSizedResource(size, photoCoordinates);
            if(lastModified>0 &&webRequest.checkNotModified(lastModified)) {
                notModified = true;
            } else {
                inputStream = storageService.loadSizedResource(size, photoCoordinates);
            }
        }
        //
        if(notModified) {
            return new ResponseEntity<byte[]>(HttpStatus.NOT_MODIFIED);
        }
        //
        return WebUtils.getResponseEntity(FileCopyUtils.copyToByteArray(inputStream), MediaType.IMAGE_PNG);
    }

}
