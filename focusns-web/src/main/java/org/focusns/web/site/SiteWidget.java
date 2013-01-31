
package org.focusns.web.site;

import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectFeature;
import org.focusns.service.core.ProjectCategoryService;
import org.focusns.service.core.ProjectFeatureService;
import org.focusns.web.widget.annotation.BeforeFilter;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.focusns.web.widget.filter.ExceptionRequiredFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindException;

import java.util.List;
import java.util.Map;

@Widget
public class SiteWidget {

    @Autowired
    private ProjectCategoryService categoryService;
    @Autowired
    private ProjectFeatureService featureService;
    
    public String mainMenu(Map<String, Object> model) {
        List<ProjectCategory> categories = categoryService.listCategories(true);
        model.put("categories", categories);
        return "site/menu-main";
    }

    public String userMenu() {
        return "site/menu-user";
    }
    
    public String projectMenu(@Bind(value="project", scope = Bind.Scope.REQUEST) 
            Project project, Map<String, Object> model) {
        List<ProjectFeature> features = featureService.getProjectFeatures(project.getId());
        model.put("features", features);
		return "site/menu-project";
	}
    
    public String copyright() {
        return "site/copyright";
    }
    
    public String login(Map<String, Object> model) {
        //
        return "site/login";
    }

    public String register() {
        return "site/register";
    }

    @BeforeFilter(ExceptionRequiredFilter.class)
    public String exception(Map<String, Object> model,
            @Bind(value="exception", scope= Bind.Scope.REQUEST) Exception e) {
        //
        if(ClassUtils.isAssignableValue(BindException.class, e)) {
            model.put("", ((BindException)e).getObjectName());
        }
        //
        return "site/exception";
    }
    
}
