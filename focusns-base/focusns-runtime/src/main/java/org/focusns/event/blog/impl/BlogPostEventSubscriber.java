package org.focusns.event.blog.impl;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

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
        if (textLength > 100) {
            text = text.substring(0, 100);
        }
        //
        blogPost = blogPostDao.select(blogPost.getId());
        blogPost.setSummary(text);
        blogPostDao.update(blogPost);
    }
}
