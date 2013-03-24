package org.focusns.web.modules.profile;

/*
 * #%L
 * FocusSNS Web
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectLink;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectLinkService;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.annotation.Constraints;
import org.focusns.web.widget.annotation.WidgetAttribute;
import org.focusns.web.widget.annotation.WidgetPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectLinkWidget {
    
    @Autowired
    private ProjectLinkService projectLinkService;

    @RequestMapping("/link-edit")
    @Constraints({Constraint.PROJECT_NOT_MY_PROFILE})
    public String doEdit(@WidgetAttribute ProjectUser user,
                         @WidgetAttribute Project project, Model model) {
        //
        ProjectLink projectLink = projectLinkService.getProjectLink(user.getProjectId(), project.getId());
        model.addAttribute("projectLink", projectLink);
        model.addAttribute("fromProject", user.getProject());
        model.addAttribute("toProject", project);
        //
        return "modules/profile/link-edit";
    }

    @RequestMapping("/link-list")
    @Constraints({Constraint.PROJECT_REQUIRED})
    public String doList(@WidgetPreference(defaultValue = "6") Integer limit,
                         @WidgetPreference(defaultValue = "false") Boolean reverse,
                         @WidgetAttribute Project project, Model model) {
        //
        Page<ProjectLink> page = new Page<ProjectLink>(limit);
        if(reverse.booleanValue()) {
            page = projectLinkService.fetchPageByToProjectId(page, project.getId());
        } else {
            page = projectLinkService.fetchPageByFromProjectId(page, project.getId());
        }
        model.addAttribute(Page.KEY, page);
        model.addAttribute("reverse", reverse.booleanValue());
        //
        return "modules/profile/link-list";
    }

    @RequestMapping("/link/create")
    public void doCreate(ProjectLink link) {
        projectLinkService.createProjectLink(link);
    }

    @RequestMapping("/link/remove")
    public void doRemove(ProjectLink link) {
        projectLinkService.removeProjectLink(link.getFromProjectId(), link.getToProjectId());
    }
    
}
