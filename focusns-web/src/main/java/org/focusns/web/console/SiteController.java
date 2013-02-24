package org.focusns.web.console;

import org.focusns.model.core.ProjectCategory;
import org.focusns.service.core.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("consoleSiteController")
@RequestMapping("/console")
public class SiteController {
    
    @Autowired
    private ProjectCategoryService categoryService;
    
    @RequestMapping("/category")
    public void editCategory(ProjectCategory category) {
    	if(category.getId()>0) {
            categoryService.modifyCategory(category);
        } else {
            categoryService.createCategory(category);
        }
    }
    
}
