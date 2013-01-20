/*
 * Copyright (C) 2011-2013 FocusSNS.
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
package org.focusns.web.modules.blog;

import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.service.blog.BlogPostService;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Bind.Scope;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Widget
public class BlogPostWidget {

    @Autowired
    private BlogPostService blogPostService;
    
	public String view(Map<String, Object> model,
                       @Bind(value = "id", scope = Scope.PARAMETER) long postId) {
        //
        BlogPost blogPost = blogPostService.getBlogPost(postId);
        model.put("blogPost", blogPost);
        model.put("blogPost", blogPost);
        //
		return "blog/post-view";
	}
	
	public String list(Map<String, Object> model,
            @Bind(value = "project", scope = Scope.SESSION) Project project,
            @Bind(value = "categoryId", scope = Scope.PARAMETER) long categoryId) {
        //
        Page<BlogPost> page = new Page<BlogPost>(20);
        if(categoryId > 0) {
            page = blogPostService.fetchPageByCategoryId(page, categoryId);
        } else {
            page = blogPostService.fetchPageByProjectId(page, project.getId());
        }
        model.put("page", page);
        //
		return "blog/post-list";
	}
	
}
