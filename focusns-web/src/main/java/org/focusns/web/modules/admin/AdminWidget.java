package org.focusns.web.modules.admin;

import java.util.List;
import java.util.Map;

import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectFeature;
import org.focusns.service.core.ProjectFeatureService;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

@Widget
public class AdminWidget {
    
    @Autowired
    private ProjectFeatureService featureService;
    
    public String menu(@Bind(value="project", scope = Bind.Scope.REQUEST)
            Project project, Map<String, Object> model) {
         //
        List<ProjectFeature> features = featureService.getProjectFeatures(project.getId());
        model.put("features", features);
        //
        return "modules/admin/menu";
    }
    
    public String summary() {
        //
        return "modules/admin/summary";
    }
     
}
