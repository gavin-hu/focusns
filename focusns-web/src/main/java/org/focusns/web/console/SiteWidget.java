package org.focusns.web.console;

import java.util.List;
import java.util.Map;

import org.focusns.model.core.ProjectCategory;
import org.focusns.service.core.ProjectCategoryService;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

@Widget("consoleSiteWidget")
public class SiteWidget {

    @Autowired
    private ProjectCategoryService categoryService;

    public String menuItems(Map<String, Object> model) {
        List<ProjectCategory> categories = categoryService.listCategories(true);
        model.put("categories", categories);
        return "console/menu-items";
    }
    
    public String menuItem(@Bind("id") long id, Map<String, Object> model) {
       ProjectCategory category = new ProjectCategory();
        if(id>0) {
            category = categoryService.getCategory(id);
        }
        model.put("category", category);
        return "console/menu-item";
    }
    
}
