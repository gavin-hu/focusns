package org.focusns.event.blog.impl;

import org.focusns.common.event.annotation.Event;
import org.focusns.common.event.annotation.EventSubscriber;
import org.focusns.common.event.support.EventContext;
import org.focusns.common.html.HtmlUtils;
import org.focusns.dao.blog.BlogPostDao;
import org.focusns.model.blog.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;

@EventSubscriber
public class BlogPostEventSubscriber {

    @Event(on = "CREATE_BLOG_POST", point = Event.Point.AFTER)
    public void afterCreateBlogPost(EventContext eventContext) {
        BlogPost blogPost = (BlogPost) eventContext.getArguments()[0];
    }

    @Event(on = "MODIFY_BLOG_POST", point = Event.Point.AFTER)
    public void afterModifyBlogPost(EventContext eventContext) {
        BlogPostDao blogPostDao = eventContext.getApplicationContext().getBean(BlogPostDao.class);
        BlogPost blogPost = (BlogPost) eventContext.getArguments()[0];
        String text = HtmlUtils.extractText(blogPost.getContent());
        //
        int textLength = text.length();
        if(textLength > 100) {
            text = text.substring(0, 100);
        }
        //
        blogPost = blogPostDao.select(blogPost.getId());
        blogPost.setSummary(text);
        blogPostDao.update(blogPost);
    }
}
