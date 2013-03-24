package org.focusns.web.cms;

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
import org.focusns.service.blog.BlogCategoryService;
import org.focusns.service.blog.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cms")
public class ArticleWidget {

    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping("/article-list")
    public String doList() {
        return "cms/article-list";
    }

    @RequestMapping("/article-view")
    public String doView(@RequestParam Long id, Model model) {
        //
        BlogPost blogPost = blogPostService.getBlogPost(id);
        model.addAttribute("article", blogPost);
        //
        return "cms/article-view";
    }

}
