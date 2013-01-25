/*
 * Copyright (C) 2011-2013 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.web.modules.profile;

import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAttribute;
import org.focusns.model.core.ProjectLink;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectAttributeService;
import org.focusns.service.core.ProjectLinkService;
import org.focusns.web.widget.annotation.BeforeFilter;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.focusns.web.widget.filter.NotMyProjectFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Widget
public class ProjectWidget {
    
    @Autowired
    private ProjectLinkService projectLinkService;
    @Autowired
    private ProjectAttributeService projectAttributeService;
    
	public String view(Map<String, Object> model,
            @Bind(value="project", scope = Bind.Scope.SESSION) Project project) {
        //
        List<ProjectAttribute> attributes = projectAttributeService.getProjectAttributes(project.getId());
        model.put("attributes", attributes);
        //
        return "profile/project-view";
    }

    public String status(Map<String, Object> model,
             @Bind(value="project", scope = Bind.Scope.SESSION) Project project) {
        //
        return "profile/project-status";
    }

    @BeforeFilter(NotMyProjectFilter.class)
    public String action(Map<String, Object> model,
                         @Bind(value="user", scope = Bind.Scope.SESSION) ProjectUser user,
                         @Bind(value="project", scope = Bind.Scope.SESSION) Project project) {
        //
        ProjectLink projectLink = projectLinkService.getProjectLink(user.getProjectId(), project.getId());
        model.put("projectLink", projectLink);
        model.put("fromProject", user.getProject());
        model.put("toProject", project);
        //
        return "profile/project-action";
    }
	
}
