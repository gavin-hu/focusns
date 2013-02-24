package org.focusns.web.modules.profile;

import java.util.List;
import java.util.Map;

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
        return "modules/profile/project-view";
    }

    public String status(Map<String, Object> model,
             @Bind(value="project", scope = Bind.Scope.SESSION) Project project) {
        //
        return "modules/profile/project-status";
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
        return "modules/profile/project-action";
    }
	
}
