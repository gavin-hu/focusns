package org.focusns.web.modules.photo;

import org.focusns.common.image.ImageUtils;
import org.focusns.common.web.WebUtils;
import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.model.photo.Album;
import org.focusns.model.photo.Photo;
import org.focusns.service.photo.AlbumService;
import org.focusns.service.photo.PhotoService;
import org.focusns.web.helper.Coordinate;
import org.focusns.web.helper.RuntimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Widget
@RequestMapping("photo")
public class PhotoWidget {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private AlbumService albumService;

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
    public void doRemove(Photo photo) {
        photoService.removePhoto(photo);
        Coordinate photoCoordinate = getPhotoCoordinate(photo);
        RuntimeHelper.cleanTargetFile(photoCoordinate);
        //
        Navigator.get().withAttribute("photo", photo).navigateTo("photo-removed");
    }

    @RequestMapping("photo-upload")
    public void doUpload(Photo photo, MultipartFile file) throws IOException {
        //
        if(!file.isEmpty()) {
            //
            photoService.createPhoto(photo);
            Coordinate photoCoordinate = getPhotoCoordinate(photo);
            RuntimeHelper.cleanTargetFile(photoCoordinate);
            RuntimeHelper.setTargetFile(photoCoordinate, file.getInputStream());
            //
            Navigator.get().withAttribute("photo", photo).navigateTo("photo-uploaded");
        }
    }

    @RequestMapping("photo-download")
    public ResponseEntity<byte[]> doDownload(@RequestParam Long photoId, @RequestParam(required = false) Integer dimension)
            throws IOException {
        //
        Photo photo = photoService.getPhoto(photoId);
        Coordinate photoCoordinate = getPhotoCoordinate(photo);
        File targetFile = RuntimeHelper.getTargetFile(photoCoordinate);
        //
        if(dimension!=null) {
            photoCoordinate.setDimension(dimension);
            File resizedTargetFile = RuntimeHelper.getTargetFile(photoCoordinate);
            if(!resizedTargetFile.exists()) {
                ImageUtils.resize(targetFile, resizedTargetFile, dimension, dimension, "PNG");
            }
            targetFile = resizedTargetFile;
        }
        //
        return WebUtils.getResponseEntity(FileCopyUtils.copyToByteArray(targetFile), MediaType.IMAGE_PNG);
    }

    private Coordinate getPhotoCoordinate(Photo photo) {
        return new Coordinate(photo.getProjectId(), "photo", "photo", photo.getId());
    }

}
