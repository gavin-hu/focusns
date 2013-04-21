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
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
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
    private ProjectUserDao projectUserDao;

    public BlogPost getBlogPost(long postId) {
        return postDao.select(postId);
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
    }

    public void modifyBlogPost(BlogPost post) {
        post.setModifiedAt(new Date());
        postDao.update(post);
    }

    public void removeBlogPost(BlogPost post) {
        postDao.delete(post.getId());
    }

    public Page<BlogPost> fetchPageByCategoryId(Page<BlogPost> page, long projectId, long categoryId) {
        page = postDao.fetchByProjectAndCategoryId(page, projectId, categoryId);
        //
        return associateObjects(page);
    }

    public Page<BlogPost> fetchPageByProjectId(Page<BlogPost> page, long projectId) {
        page = postDao.fetchByProjectAndCategoryId(page, projectId, null);
        //
        return associateObjects(page);
    }

    private Page<BlogPost> associateObjects(Page<BlogPost> page) {
        for (BlogPost blogPost : page.getResults()) {
            ProjectUser createBy = projectUserDao.selectWithProject(blogPost.getCreatedById());
            blogPost.setCreatedBy(createBy);
            //
            ProjectUser modifyBy = projectUserDao.selectWithProject(blogPost.getModifiedById());
            blogPost.setModifiedBy(modifyBy);
        }
        //
        return page;
    }

}
