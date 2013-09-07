package org.focusns.web.modules.photo;

import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.model.photo.Album;
import org.focusns.service.photo.AlbumService;
import org.focusns.web.widget.constraint.annotation.RequiresProject;
import org.focusns.web.widget.constraint.annotation.RequiresProjectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Widget
@RequestMapping("photo")
public class AlbumWidget {

    @Autowired
    private AlbumService albumService;

    @RequiresProject
    @RequestMapping("album-list")
    public String doList(@WidgetAttribute Project project, Model model) {
        //
        List<Album> albums = albumService.listAlbums(project.getId());
        model.addAttribute("albums", albums);
        //
        return "modules/photo/album-list";
    }

    @RequiresProject
    @RequiresProjectUser
    @RequestMapping("album-edit")
    public String doEdit(@RequestParam(required = false) Long albumId, @WidgetAttribute Project project, @WidgetAttribute ProjectUser projectUser, Model model) {
        //
        Album album = new Album();
        if(albumId==null) {
            album.setProjectId(project.getId());
            album.setCreatedById(projectUser.getId());
            album.setModifiedById(projectUser.getId());
        } else {
            album = albumService.getAlbum(albumId);
        }
        model.addAttribute("album", album);
        //
        return "modules/photo/album-edit";
    }

    @RequestMapping({"album-create", "album-modify"})
    public void doModify(Album album) {
        //
        Navigator.get().withAttribute("album", album);
        if(album.getId()>0) {
            albumService.modifyAlbum(album);
            Navigator.get().navigateTo("album-modified");
        } else {
            albumService.createAlbum(album);
            Navigator.get().navigateTo("album-created");
        }
    }

}
