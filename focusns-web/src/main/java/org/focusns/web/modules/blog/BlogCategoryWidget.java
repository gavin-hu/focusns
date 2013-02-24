package org.focusns.web.modules.blog;

import java.util.List;
import java.util.Map;

import org.focusns.model.blog.BlogCategory;
import org.focusns.model.core.Project;
import org.focusns.service.blog.BlogCategoryService;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

@Widget
public class BlogCategoryWidget {
    
    @Autowired
    private BlogCategoryService blogCategoryService;
    
    public String list(Map<String, Object> model,
            @Bind(value="project", scope = Bind.Scope.REQUEST) Project project) {
        //
    	Long projectId = project.getId();
    	//
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setLabel("未分类");
        blogCategory.setProjectId(projectId);
        List<BlogCategory> blogCategories = blogCategoryService.getBlogCategories(projectId);
        blogCategories.add(0, blogCategory);
        model.put("blogCategories", blogCategories);
        //
        return "modules/blog/category-list";
    }
    
}
