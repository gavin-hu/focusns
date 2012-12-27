package org.focusns.service.blog;

import java.util.List;
import org.focusns.model.blog.BlogTag;
import org.focusns.model.core.Project;
import org.focusns.service.AbstractServiceTest;
import org.focusns.service.core.ProjectService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class BlogTagServiceTest extends AbstractServiceTest {
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BlogTagService blogTagService;
    
    @Test
    public void testCreateBlogTag() {
        Project project = projectService.getProject("focusns");
        Assert.assertNotNull(project);
        //
        BlogTag blogTag = new BlogTag(project.getId(), "未分类");
        blogTagService.createBlogTag(blogTag);
    }
    
    @Test
    public void testRemoteBlogTag() {
        Project project = projectService.getProject("focusns");
        Assert.assertNotNull(project);
        //
        List<BlogTag> blogTags = blogTagService.getBlogTags(project.getId());
        for(BlogTag blogTag : blogTags) {
            blogTagService.removeBlogTag(blogTag);
        }
    }
    
}
