package org.focusns.web.modules.team;

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

import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectRole;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Widget
@RequestMapping("/team")
public class TeamRoleWidget {

    @Autowired
    private ProjectRoleService projectRoleService;

    @RequestMapping(value="/role-list", method = GET)
    public String doList(@WidgetAttribute Project project, Model model) {
        //
        List<ProjectRole> projectRoles = projectRoleService.listProjectRoles(project.getId());
        model.addAttribute("projectRoles", projectRoles);
        //
        return "modules/team/role-list";
    }

    @RequestMapping(value="/role-edit", method = GET)
    public String doEdit(@RequestParam(required = false) Long roleId, @WidgetAttribute Project project,
                         @WidgetAttribute ProjectUser projectUser, Model model) {
        //
        ProjectRole projectRole = new ProjectRole();
        if(roleId==null) {
            projectRole.setProjectId(project.getId());
        } else {
            projectRole = projectRoleService.getProjectRole(roleId);
        }
        //
        List<ProjectRole> projectRoles = projectRoleService.listProjectRoles(project.getId());
        //
        model.addAttribute("projectRole", projectRole);
        model.addAttribute("projectRoles", projectRoles);
        //
        return "modules/team/role-edit";
    }

    @RequestMapping(value={ "/role-create", "/role-modify" }, method = POST)
    public void doModify(ProjectRole projectRole) {
        //
        Navigator.get().withAttribute("projectRole", projectRole);
        if(projectRole.getId()>0) {
            projectRoleService.modifyProjectRole(projectRole);
            Navigator.get().navigateTo("role-modified");
        } else {
            projectRoleService.createProjectRole(projectRole);
            Navigator.get().navigateTo("role-created");
        }
    }

    @RequestMapping(value="/role-remove", method = GET)
    public void doRemove(@RequestParam Long id) {
        ProjectRole projectRole = projectRoleService.getProjectRole(id);
        projectRoleService.removeProjectRole(projectRole);
        Navigator.get().withAttribute("projectRole", projectRole).navigateTo("role-removed");
    }

}
