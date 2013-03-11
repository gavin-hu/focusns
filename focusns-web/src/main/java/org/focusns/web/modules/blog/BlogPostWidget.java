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


import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
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

    @RequestMapping("/post-view")
	public String doView(@RequestParam long postId, Model model) {
        //
        BlogPost blogPost = blogPostService.getBlogPost(postId);
        model.addAttribute("blogPost", blogPost);
        //
		return "modules/blog/post-view";
	}

    @RequestMapping("/post-list")
	public String doList(@RequestParam(required = false) Long categoryId,
                         Project project, Model model) {
        //
        Page<BlogPost> page = new Page<BlogPost>(20);
        if(categoryId != null) {
            page = blogPostService.fetchPageByCategoryId(page, categoryId);
        } else {
            page = blogPostService.fetchPageByProjectId(page, project.getId());
        }
        model.addAttribute("page", page);
        //
		return "modules/blog/post-list";
	}
	
}
