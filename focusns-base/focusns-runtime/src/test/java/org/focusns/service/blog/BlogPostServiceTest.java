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

import java.util.Date;

import org.focusns.model.blog.BlogCategory;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.AbstractServiceTest;
import org.focusns.service.core.ProjectService;
import org.focusns.service.core.ProjectUserService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class BlogPostServiceTest extends AbstractServiceTest {

    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private BlogCategoryService tagService;
    @Autowired
    private BlogPostService postService;

    @Test
    public void testCreateBlogPost() {
        Project project = projectService.getProject("focusns");
        Assert.assertNotNull(project);
        //
        BlogCategory blogTag = new BlogCategory();
        blogTag.setLabel("未分类");
        blogTag.setProjectId(project.getId());
        //
        tagService.createBlogCategory(blogTag);
        //
        ProjectUser user = projectUserService.getUser("admin");
        Assert.assertNotNull(user);
        //
        Date now = new Date();
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("Blog Title");
        blogPost.setContent("Blog Content");
        blogPost.setCreatedAt(now);
        blogPost.setModifiedAt(now);
        blogPost.setCategoryId(blogTag.getId());
        blogPost.setCreatedById(user.getId());
        //
        postService.createBlogPost(blogPost);
    }

    @Test
    public void testRemoveBlogPost() {
        Project project = projectService.getProject("focusns");
        Assert.assertNotNull(project);
        //
        Page<BlogPost> page = new Page<BlogPost>(10);
        page = postService.fetchPageByProjectId(page, project.getId());
        for (BlogPost post : page.getResults()) {
            postService.removeBlogPost(post);
        }
    }

}
