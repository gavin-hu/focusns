package org.focusns.web.modules.photo;

import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.Project;
import org.focusns.model.photo.Album;
import org.focusns.service.photo.AlbumService;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.Constraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Widget
@RequestMapping("photo")
public class PhotoMenuWidget {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("menu-user")
    @Constraints({ Constraint.PROJECT_NOT_NULL, Constraint.PROJECT_USER_NOT_NULL})
    public String doMenu() {
        return "modules/photo/menu-user";
    }

    @RequestMapping("menu-view")
    @Constraints({ Constraint.PROJECT_NOT_NULL})
    public String doView(@WidgetAttribute Project project, Model model) {
        //
        List<Album> albums = albumService.listAlbums(project.getId());
        model.addAttribute("albums", albums);
        //
        return "modules/photo/menu-view";
    }

}
