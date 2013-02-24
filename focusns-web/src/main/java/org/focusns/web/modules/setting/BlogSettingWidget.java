package org.focusns.web.modules.setting;

import java.util.List;
import java.util.Map;

import org.focusns.model.blog.BlogCategory;
import org.focusns.model.core.Project;
import org.focusns.service.blog.BlogCategoryService;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

@Widget
public class BlogSettingWidget {
    
    @Autowired
    private BlogCategoryService categoryService;
    
    public String editCategory(Map<String, Object> model,
            @Bind(value="project", scope= Bind.Scope.SESSION) Project project) {
        //
        List<BlogCategory> blogCategories = categoryService.getBlogCategories(project.getId());
        model.put("blogCategories", blogCategories);
        //
        return "modules/setting/blog/category-edit";
    }
    
    public String editPost(Map<String, Object> model,
            @Bind(value="id", scope= Bind.Scope.PARAMETER) long id,
            @Bind(value="project", scope= Bind.Scope.SESSION) Project project) {
        //
        List<BlogCategory> blogCategories = categoryService.getBlogCategories(project.getId());
        model.put("blogCategories", blogCategories);
        //
        return "modules/setting/blog/post-edit";
    }
    
}
