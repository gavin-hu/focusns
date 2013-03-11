package org.focusns.service.blog;

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


import org.focusns.model.blog.BlogCategory;
import org.focusns.model.core.Project;
import org.focusns.service.AbstractServiceTest;
import org.focusns.service.core.ProjectService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Ignore
public class BlogCategoryServiceTest extends AbstractServiceTest {
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BlogCategoryService blogCategoryService;
    
    @Test
    public void testCreateBlogCategory() {
        Project project = projectService.getProject("admin");
        Assert.assertNotNull(project);
        //
        BlogCategory blogCategory = new BlogCategory(project.getId(), "Test");
        blogCategory.setCreateAt(new Date());
        blogCategory.setCreateById(1);

        blogCategoryService.createBlogCategory(blogCategory);
    }
    
    @Test
    public void testRemoveBlogCategory() {
        Project project = projectService.getProject("admin");
        Assert.assertNotNull(project);
        //
        List<BlogCategory> blogCategories = blogCategoryService.getBlogCategories(project.getId());
        for(BlogCategory blogCategory : blogCategories) {
            blogCategoryService.removeBlogCategory(blogCategory);
        }
    }

    @Test
    public void testCreateBlogCategoryNoProject() {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setCreateById(1);
        blogCategory.setCreateAt(new Date());
        blogCategory.setLabel("News");
        blogCategoryService.createBlogCategory(blogCategory);

    }

    @Test
    public void testGetBlogCategoriesNoProject() {
         List<BlogCategory> blogCategories = blogCategoryService.getBlogCategories();
        System.out.println(blogCategories.size());
    }

}
