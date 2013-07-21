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

import java.util.List;

import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.model.blog.BlogCategory;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.blog.BlogCategoryService;
import org.focusns.service.blog.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class BlogPostWidget {

    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping("/post-edit")
    public String doEdit(@RequestParam(required = false) Long postId, @RequestParam(required = false) Long categoryId,
            @WidgetAttribute ProjectUser projectUser, @WidgetAttribute Project project, Model model) {
        //
        List<BlogCategory> blogCategories = blogCategoryService.getBlogCategories(project.getId());
        //
        BlogPost blogPost = null;
        if (postId == null) {
            blogPost = new BlogPost();
            blogPost.setProjectId(project.getId());
            blogPost.setCreatedById(projectUser.getId());
            blogPost.setModifiedById(projectUser.getId());
            blogPost.setCategoryId(categoryId != null ? categoryId : 0);
        } else {
            blogPost = blogPostService.getBlogPost(postId);
        }
        //
        model.addAttribute("blogPost", blogPost);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("blogCategories", blogCategories);
        //
        return "modules/blog/post-edit";
    }

    @RequestMapping("/post-view")
    public String doView(@RequestParam Long postId, Model model) {
        //
        BlogPost blogPost = blogPostService.getBlogPost(postId);
        model.addAttribute("blogPost", blogPost);
        //
        return "modules/blog/post-view";
    }

    @RequestMapping("/post-list")
    public String doList(@RequestParam(required = false, defaultValue = "0") Long categoryId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo, @WidgetAttribute Project project, Model model) {
        //
        Page<BlogPost> page = new Page<BlogPost>(10);
        page.setPageNo(pageNo);
        if (categoryId == 0) {
            page = blogPostService.fetchPageByProjectId(page, project.getId());
        } else {
            page = blogPostService.fetchPageByCategoryId(page, project.getId(), categoryId);
        }
        model.addAttribute("page", page);
        //
        return "modules/blog/post-list";
    }

    @RequestMapping("/post-modify")
    public void doModify(BlogPost blogPost) {
        Navigator.get().withAttribute("blogPost", blogPost);
        if (blogPost.getId() > 0) {
            blogPostService.modifyBlogPost(blogPost);
            Navigator.get().navigateTo("post-modified");
        } else {
            blogPostService.createBlogPost(blogPost);
            Navigator.get().navigateTo("post-created");
        }
    }

}
