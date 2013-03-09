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
import org.focusns.model.core.ProjectHistory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectHistoryService;
import org.focusns.web.widget.annotation.WidgetAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectHistoryWidget {
    
    @Autowired
    private ProjectHistoryService historyService;

    @RequestMapping("/history-create")
    public void doCreate(ProjectHistory history) {
        historyService.createProjectHistory(history);
    }

    @RequestMapping("/history-form")
    public String doEdit(Project project, ProjectUser user, Model model) {
        //
        ProjectHistory template = createTemplate(user, project);
        model.addAttribute("template", template);
        //
        return "modules/profile/history-form";
    }

    @RequestMapping("/history-list")
    public String doList(@WidgetAttribute Project project,
                         @WidgetAttribute ProjectUser user, Model model) {
        //
        if(user!=null) {
            ProjectHistory template = createTemplate(user, project);
            model.addAttribute("template", template);
        }
        //
        Page<ProjectHistory> page = new Page<ProjectHistory>(20);
        page = historyService.fetchPage(page, project.getId());
        model.addAttribute("page", page);
        //
        return "modules/profile/history-list";
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
