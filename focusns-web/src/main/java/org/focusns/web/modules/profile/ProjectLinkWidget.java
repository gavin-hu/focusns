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
import org.focusns.web.widget.annotation.AfterFilter;
import org.focusns.web.widget.annotation.BeforeFilter;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.focusns.web.widget.filter.NotMyProjectFilter;
import org.focusns.web.widget.filter.PageRequiredFilter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Widget
public class ProjectLinkWidget {
    
    @Autowired
    private ProjectLinkService projectLinkService;
    
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
        return "modules/profile/link-action";
    }

    @AfterFilter(PageRequiredFilter.class)
    public String list(Map<String, Object> model,
                       @Bind(value="limit", scope = Bind.Scope.PREFERENCE) int limit,
                       @Bind(value="project", scope = Bind.Scope.SESSION) Project project) {
        //
        Page<ProjectLink> page = new Page<ProjectLink>(limit);
        page = projectLinkService.fetchPageByFromProjectId(page, project.getId());
        model.put("page", page);
        //
        return "modules/profile/link-list";
    }
    
}
