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

import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectHistory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectHistoryService;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Resource;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Widget
public class ProjectHistoryWidget {
    
    @Autowired
    private ProjectHistoryService projectHistoryService;

    @Resource(required="user", scope= Resource.Scope.SESSION)
    public String input(Map<String, Object> model,
                        @Bind(value="user", scope = Bind.Scope.SESSION) ProjectUser user,
                        @Bind(value="project", scope = Bind.Scope.SESSION) Project project) {
        //
        ProjectHistory template = createTemplate(user, project);
        model.put("template", template);
        //
        return "profile/stream-input";
    }
    
    public String output(Map<String, Object> model,
                         @Bind(value="user", scope = Bind.Scope.SESSION) ProjectUser user,
                         @Bind(value="project", scope = Bind.Scope.SESSION) Project project) {
        //
        ProjectHistory template = createTemplate(user, project);
        model.put("template", template);
        //
        Page<ProjectHistory> page = new Page<ProjectHistory>(20);
        page = projectHistoryService.fetchPage(page, project.getId());
        model.put("page", page);
        //
        return "profile/stream-output";
    }

    private ProjectHistory createTemplate(ProjectUser user, Project project) {
        ProjectHistory template = new ProjectHistory();
        template.setProjectId(project.getId());
        template.setTargetId(project.getId());
        template.setTargetType("project");
        template.setCreateById(user.getId());
        return template;
    }
    
}
