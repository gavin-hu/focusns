package org.focusns.web.modules.profile;

import java.util.Map;

import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectHistory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectHistoryService;
import org.focusns.web.widget.annotation.AfterFilter;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Resource;
import org.focusns.web.widget.annotation.Widget;
import org.focusns.web.widget.filter.PageRequiredFilter;
import org.springframework.beans.factory.annotation.Autowired;

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
        return "modules/profile/stream-input";
    }

    @AfterFilter(PageRequiredFilter.class)
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
        return "modules/profile/stream-output";
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
