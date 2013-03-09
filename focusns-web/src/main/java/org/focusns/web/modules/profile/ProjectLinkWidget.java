package org.focusns.web.modules.profile;

/*
 * #%L
 * FocusSNS Web
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectLink;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class ProjectLinkWidget {
    
    @Autowired
    private ProjectLinkService projectLinkService;
    
    public String action(Map<String, Object> model,
                         ProjectUser user,
                         Project project) {
        //
        ProjectLink projectLink = projectLinkService.getProjectLink(user.getProjectId(), project.getId());
        model.put("projectLink", projectLink);
        model.put("fromProject", user.getProject());
        model.put("toProject", project);
        //
        return "modules/profile/link-action";
    }

    public String list(Map<String, Object> model,
                       int limit,
                       Project project) {
        //
        Page<ProjectLink> page = new Page<ProjectLink>(limit);
        page = projectLinkService.fetchPageByFromProjectId(page, project.getId());
        model.put("portal", page);
        //
        return "modules/profile/link-list";
    }
    
}
