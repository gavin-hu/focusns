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
import java.util.List;

import org.focusns.dao.blog.BlogCategoryDao;
import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.blog.BlogCategory;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.blog.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private BlogCategoryDao categoryDao;

    @Override
    public BlogCategory getBlogCategory(long categoryId) {
        BlogCategory category = categoryDao.select(categoryId);
        return fillBlogCategory(category);
    }

    public void createBlogCategory(BlogCategory category) {
        if (category.getCreatedAt() == null) {
            category.setCreatedAt(new Date());
        }
        categoryDao.insert(category);
        //
        fillBlogCategory(category);
    }

    public void modifyBlogCategory(BlogCategory category) {
        categoryDao.update(category);
        fillBlogCategory(category);
    }

    public void removeBlogCategory(BlogCategory category) {
        categoryDao.delete(category.getId());
    }

    public List<BlogCategory> getBlogCategories(long projectId) {
        List<BlogCategory> categories = categoryDao.selectByProjectId(projectId);
        for(BlogCategory category : categories) {
            fillBlogCategory(category);
        }
        return categories;
    }

    @Override
    public List<BlogCategory> getBlogCategories() {
        List<BlogCategory> categories = categoryDao.selectByProjectId(0);
        for(BlogCategory category : categories) {
            fillBlogCategory(category);
        }
        return categories;
    }

    private BlogCategory fillBlogCategory(BlogCategory blogCategory) {
        if(blogCategory==null) {
            return blogCategory;
        }
        if(blogCategory.getProject()==null && blogCategory.getProjectId()>0) {
            Project project = projectDao.select(blogCategory.getProjectId());
            blogCategory.setProject(project);
        }
        if(blogCategory.getCreatedBy()==null && blogCategory.getCreatedById()>0) {
            ProjectUser createdBy = projectUserDao.select(blogCategory.getCreatedById());
            blogCategory.setCreatedBy(createdBy);
        }
        return blogCategory;
    }

}
