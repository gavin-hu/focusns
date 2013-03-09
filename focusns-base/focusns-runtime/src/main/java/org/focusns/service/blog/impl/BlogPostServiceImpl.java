package org.focusns.service.blog.impl;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


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

    public BlogPost getBlogPost(long postId) {
        return postDao.select(postId);
    }

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
        return postDao.fetchByProjectAndCategoryId(page, null, categoryId);
    }

    public Page<BlogPost> fetchPageByProjectId(Page<BlogPost> page, long projectId) {
        return postDao.fetchByProjectAndCategoryId(page, projectId, null);
    }
    
}
