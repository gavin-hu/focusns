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

import java.util.List;
import org.focusns.dao.blog.BlogTagDao;
import org.focusns.model.blog.BlogTag;
import org.focusns.service.blog.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogTagServiceImpl implements BlogTagService {

    @Autowired
    private BlogTagDao tagDao;
    
    public void createBlogTag(BlogTag tag) {
        tagDao.insert(tag);
    }

    public void modifyBlogTag(BlogTag tag) {
        tagDao.update(tag);
    }

    public void removeBlogTag(BlogTag tag) {
        tagDao.delete(tag.getId());
    }

    public List<BlogTag> getBlogTags(long projectId) {
        return tagDao.selectByProjectId(projectId);
    }
    
}
