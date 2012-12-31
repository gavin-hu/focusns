/*
 * Copyright (C) 2012 FocusSNS.
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
import org.focusns.dao.blog.BlogCategoryDao;
import org.focusns.model.blog.BlogCategory;
import org.focusns.service.blog.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private BlogCategoryDao tagDao;
    
    public void createBlogCategory(BlogCategory tag) {
        tagDao.insert(tag);
    }

    public void modifyBlogCategory(BlogCategory tag) {
        tagDao.update(tag);
    }

    public void removeBlogCategory(BlogCategory tag) {
        tagDao.delete(tag.getId());
    }

    public List<BlogCategory> getBlogCategories(long projectId) {
        return tagDao.selectByProjectId(projectId);
    }
    
}
