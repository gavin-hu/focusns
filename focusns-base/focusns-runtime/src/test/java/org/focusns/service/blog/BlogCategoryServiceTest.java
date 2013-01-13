package org.focusns.service.blog;

import org.focusns.model.blog.BlogCategory;
import org.focusns.model.core.Project;
import org.focusns.service.AbstractServiceTest;
import org.focusns.service.core.ProjectService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Ignore
public class BlogCategoryServiceTest extends AbstractServiceTest {
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BlogCategoryService blogCategoryService;
    
    @Test
    public void testCreateBlogCategory() {
        Project project = projectService.getProject("admin");
        Assert.assertNotNull(project);
        //
        BlogCategory blogCategory = new BlogCategory(project.getId(), "Test");
        blogCategory.setCreateAt(new Date());
        blogCategory.setCreateById(1);

        blogCategoryService.createBlogCategory(blogCategory);
    }
    
    @Test
    public void testRemoveBlogCategory() {
        Project project = projectService.getProject("admin");
        Assert.assertNotNull(project);
        //
        List<BlogCategory> blogCategories = blogCategoryService.getBlogCategories(project.getId());
        for(BlogCategory blogCategory : blogCategories) {
            blogCategoryService.removeBlogCategory(blogCategory);
        }
    }
    
}
