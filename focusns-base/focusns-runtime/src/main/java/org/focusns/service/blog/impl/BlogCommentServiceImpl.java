/*
 * Copyright (C) 2012 FocuSNS.
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

import org.focusns.dao.blog.BlogCommentDao;
import org.focusns.model.blog.BlogComment;
import org.focusns.model.common.Page;
import org.focusns.service.blog.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogCommentServiceImpl implements BlogCommentService {

    @Autowired
    private BlogCommentDao commentDao;
    
    public void createBlogComment(BlogComment comment) {
        commentDao.insert(comment);
    }

    public void modifyBlogComment(BlogComment comment) {
        commentDao.update(comment);
    }

    public void removeBlogComment(BlogComment comment) {
        commentDao.delete(comment.getId());
    }

    public Page<BlogComment> fetchPageByPostId(Page<BlogComment> page, long postId) {
        return commentDao.fetchByPostId(page, postId);
    }
    
}
