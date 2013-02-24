package org.focusns.web.modules.profile;

import java.util.Map;

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
