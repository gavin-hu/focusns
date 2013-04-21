package org.focusns.event.blog;

import org.focusns.event.AbstractEventTest;
import org.focusns.event.blog.impl.BlogPostEventSubscriber;
import org.focusns.model.blog.BlogPost;
import org.focusns.service.blog.BlogPostService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.Introspector;

@Ignore
public class BlogPostEventSubscriberTest extends AbstractEventTest {

    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private BlogPostEventSubscriber blogPostEventSubscriber;

    @Test
    public void testGetBlogPostEvent() {
        //
        BlogPost blogPost = blogPostService.getBlogPost(1);
        Assert.assertTrue(blogPost.getId()==1);
        //
        Assert.assertNotNull(blogPostEventSubscriber);
    }

}
