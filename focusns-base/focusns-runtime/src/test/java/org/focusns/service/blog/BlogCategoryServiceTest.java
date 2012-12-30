package org.focusns.service.blog;

import java.util.List;
import org.focusns.model.blog.BlogCategory;
import org.focusns.model.core.Project;
import org.focusns.service.AbstractServiceTest;
import org.focusns.service.core.ProjectService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class BlogCategoryServiceTest extends AbstractServiceTest {
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BlogCategoryService blogCategoryService;
    
    @Test
    public void testCreateBlogTag() {
        Project project = projectService.getProject("focusns");
        Assert.assertNotNull(project);
        //
        BlogCategory blogTag = new BlogCategory(project.getId(), "未分类");
        blogCategoryService.createBlogTag(blogTag);
    }
    
    @Test
    public void testRemoteBlogTag() {
        Project project = projectService.getProject("focusns");
        Assert.assertNotNull(project);
        //
        List<BlogCategory> blogTags = blogCategoryService.getBlogTags(project.getId());
        for(BlogCategory blogTag : blogTags) {
            blogCategoryService.removeBlogTag(blogTag);
        }
    }
    
}
