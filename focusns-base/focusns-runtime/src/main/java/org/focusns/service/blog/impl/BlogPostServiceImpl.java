package org.focusns.service.blog.impl;

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

import java.util.Date;

import org.focusns.dao.blog.BlogPostDao;
import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.blog.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostDao postDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;

    public BlogPost getBlogPost(long postId) {
        BlogPost blogPost = postDao.select(postId);
        return fillBlogPost(blogPost);
    }

    public void createBlogPost(BlogPost post) {
        Date now = new Date();
        if (post.getCreatedAt() == null) {
            post.setCreatedAt(now);
        }
        if (post.getModifiedAt() == null) {
            post.setModifiedAt(now);
        }
        //
        if (post.getSummary() == null) {
            post.setSummary("Summary!");
        }
        //
        postDao.insert(post);
        //
        fillBlogPost(post);
    }

    public void modifyBlogPost(BlogPost post) {
        post.setModifiedAt(new Date());
        postDao.update(post);
        //
        fillBlogPost(post);
    }

    public void removeBlogPost(BlogPost post) {
        postDao.delete(post.getId());
        //
        fillBlogPost(post);
    }

    public Page<BlogPost> fetchPageByCategoryId(Page<BlogPost> page, long projectId, long categoryId) {
        page = postDao.selectByProjectAndCategoryId(page, projectId, categoryId);
        //
        for (BlogPost blogPost : page.getResults()) {
            fillBlogPost(blogPost);
        }
        return page;
    }

    public Page<BlogPost> fetchPageByProjectId(Page<BlogPost> page, long projectId) {
        page = postDao.selectByProjectAndCategoryId(page, projectId, null);
        //
        for (BlogPost blogPost : page.getResults()) {
            fillBlogPost(blogPost);
        }
        return page;
    }

    private BlogPost fillBlogPost(BlogPost blogPost) {
        if (blogPost == null) {
            return blogPost;
        }
        if (blogPost.getProject() == null && blogPost.getProjectId() > 0) {
            Project project = projectDao.select(blogPost.getProjectId());
            blogPost.setProject(project);
        }
        if (blogPost.getCreatedBy() == null && blogPost.getCreatedById() > 0) {
            ProjectUser createdBy = projectUserDao.select(blogPost.getCreatedById());
            blogPost.setCreatedBy(createdBy);
        }
        if (blogPost.getModifiedBy() == null && blogPost.getModifiedById() > 0) {
            ProjectUser modifiedBy = projectUserDao.select(blogPost.getModifiedById());
            blogPost.setModifiedBy(modifiedBy);
        }
        return blogPost;
    }

}
