package org.focusns.web.modules.profile;

import org.focusns.model.core.ProjectLink;
import org.focusns.service.core.ProjectLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectLinkController {

    @Autowired
    private ProjectLinkService projectLinkService;

    @RequestMapping("/project/link/create")
    public void link(ProjectLink link) {
        projectLinkService.createProjectLink(link);
    }

    @RequestMapping("/project/link/remove")
    public void unlink(ProjectLink link) {
        projectLinkService.removeProjectLink(link.getFromProjectId(), link.getToProjectId());
    }

}
