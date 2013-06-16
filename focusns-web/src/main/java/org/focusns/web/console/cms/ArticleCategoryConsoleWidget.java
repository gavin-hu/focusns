package org.focusns.web.console.cms;

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
import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.blog.BlogCategory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.blog.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Widget
@RequestMapping("/console/cms")
public class ArticleCategoryConsoleWidget {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping("/category-list")
    public String doList(Model model) {
        //
        List<BlogCategory> articleCategories = blogCategoryService.getBlogCategories();
        model.addAttribute("articleCategories", articleCategories);
        //
        return "console/cms/category-list";
    }

    @RequestMapping("/category-edit")
    public String doEdit(@RequestParam(required = false) Long categoryId, @WidgetAttribute ProjectUser user, Model model) {
        //
        BlogCategory articleCategory = new BlogCategory();
        articleCategory.setCreatedById(user.getId());
        if (categoryId != null) {
            articleCategory = blogCategoryService.getBlogCategory(categoryId);
        }
        model.addAttribute("articleCategory", articleCategory);
        //
        return "console/cms/category-edit";
    }

    @RequestMapping("/category-modify")
    public void modifyAction(BlogCategory articleCategory) {
        if (articleCategory.getId() > 0) {
            blogCategoryService.modifyBlogCategory(articleCategory);
        } else {
            blogCategoryService.createBlogCategory(articleCategory);
        }
    }

    @RequestMapping("/category-remove")
    public void removeAction(BlogCategory articleCategory) {
        blogCategoryService.removeBlogCategory(articleCategory);
    }

}
