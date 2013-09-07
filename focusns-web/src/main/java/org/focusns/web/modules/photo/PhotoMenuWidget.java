package org.focusns.web.modules.photo;

import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.Project;
import org.focusns.model.photo.Album;
import org.focusns.service.photo.AlbumService;
import org.focusns.web.widget.constraint.annotation.RequiresProject;
import org.focusns.web.widget.constraint.annotation.RequiresProjectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Widget
@RequestMapping("photo")
public class PhotoMenuWidget {

    @Autowired
    private AlbumService albumService;

    @RequiresProject
    @RequiresProjectUser
    @RequestMapping("menu-user")
    public String doMenu() {
        return "modules/photo/menu-user";
    }

    @RequiresProject
    @RequestMapping("menu-view")
    public String doView(@WidgetAttribute Project project, Model model) {
        //
        List<Album> albums = albumService.listAlbums(project.getId());
        model.addAttribute("albums", albums);
        //
        return "modules/photo/menu-view";
    }

}
