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

import org.focusns.model.blog.BlogCategory;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.service.blog.BlogCategoryService;
import org.focusns.service.blog.BlogPostService;
import org.focusns.web.widget.annotation.WidgetPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms")
public class ArticleCategoryWidget {

    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping("/category-list")
    public String doList() {
        return "cms/category-list";
    }

    @RequestMapping("/category-view")
    public String doView(@WidgetPreference Long categoryId, @WidgetPreference Integer limit, Model model) {
        //
        BlogCategory articleCategory = blogCategoryService.getBlogCategory(categoryId);
        //
        Page<BlogPost> page = new Page<BlogPost>(limit);
        page = blogPostService.fetchPageByCategoryId(page, -1, categoryId);
        //
        model.addAttribute("articleCategory", articleCategory);
        model.addAttribute(Page.KEY, page);
        //
        return "cms/category-view";
    }

}
