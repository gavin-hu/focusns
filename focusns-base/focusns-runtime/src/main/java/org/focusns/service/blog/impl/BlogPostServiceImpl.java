/*
 * Copyright (C) 2011-2013 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.service.blog.impl;

import org.focusns.dao.blog.BlogPostDao;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.service.blog.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostDao postDao;
    
    public void createBlogPost(BlogPost post) {
        postDao.insert(post);
    }

    public void modifyBlogPost(BlogPost post) {
        postDao.update(post);
    }

    public void removeBlogPost(BlogPost post) {
        postDao.delete(post.getId());
    }

    public Page<BlogPost> fetchPageByCategoryId(Page<BlogPost> page, long categoryId) {
        return postDao.fetchByCategoryId(page, categoryId);
    }

    public Page<BlogPost> fetchPageByProjectId(Page<BlogPost> page, long projectId) {
        return postDao.fetchByProjectId(page, projectId);
    }
    
}
