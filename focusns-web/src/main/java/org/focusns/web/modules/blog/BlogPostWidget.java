package org.focusns.web.modules.blog;

/*
 * #%L
 * FocusSNS Web
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
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.blog.BlogCategoryService;
import org.focusns.service.blog.BlogPostService;
import org.focusns.web.widget.annotation.WidgetAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogPostWidget {

    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping("/post-edit")
    public String doEdit(@RequestParam(required = false) Long id, @WidgetAttribute ProjectUser projectUser, @WidgetAttribute Project project, Model model) {
        //
        List<BlogCategory> blogCategories = blogCategoryService.getBlogCategories(project.getId());
        //
        BlogPost blogPost = null;
        if (id == null) {
            blogPost = new BlogPost();
            blogPost.setCreateById(projectUser.getId());
            blogPost.setModifyById(projectUser.getId());
        } else {
            blogPost = blogPostService.getBlogPost(id);
        }
        //
        model.addAttribute("blogPost", blogPost);
        model.addAttribute("blogCategories", blogCategories);
        //
        return "modules/blog/post-edit";
    }

    @RequestMapping("/post-view")
    public String doView(@RequestParam Long id, Model model) {
        //
        BlogPost blogPost = blogPostService.getBlogPost(id);
        model.addAttribute("blogPost", blogPost);
        //
        return "modules/blog/post-view";
    }

    @RequestMapping("/post-list")
    public String doList(@RequestParam(required = false) Long categoryId, Project project, Model model) {
        //
        Page<BlogPost> page = new Page<BlogPost>(20);
        if (categoryId != null) {
            page = blogPostService.fetchPageByCategoryId(page, categoryId);
        } else {
            page = blogPostService.fetchPageByProjectId(page, project.getId());
        }
        model.addAttribute("page", page);
        //
        return "modules/blog/post-list";
    }

    @RequestMapping("/post-modify")
    public void doModify(BlogPost blogPost) {
        if (blogPost.getId() > 0) {
            blogPostService.modifyBlogPost(blogPost);
        } else {
            blogPostService.createBlogPost(blogPost);
        }
    }

}
