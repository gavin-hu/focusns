package org.focusns.event.blog.impl;

import org.focusns.common.event.annotation.Event;
import org.focusns.common.event.annotation.EventSubscriber;
import org.focusns.common.event.support.EventContext;
import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.blog.BlogCategory;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectService;
import org.focusns.service.core.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;

@EventSubscriber
public class BlogCategoryEventSubscriber {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;

    @Event(on = "CREATE_BLOG_CATEGORY", point = Event.Point.AFTER)
    public void afterCreateBlogCategory(EventContext eventContext) {
        BlogCategory blogCategory = (BlogCategory) eventContext.getArguments()[0];

    }

}
