package org.focusns.plugin.sample;

import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectFeature;
import org.focusns.service.core.ProjectFeatureService;
import org.focusns.service.core.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Widget
public class ProjectInfoWidget {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectFeatureService projectFeatureService;

    @RequestMapping("project-info")
    public String helloWord(@WidgetAttribute Project project, Model model) {
        //
        List<ProjectFeature> projectFeatures = projectFeatureService.getProjectFeatures(project.getId());
        model.addAttribute("projectFeatures", projectFeatures);
        model.addAttribute("project", project);
        //
        return "sample/project-info";
    }

}
