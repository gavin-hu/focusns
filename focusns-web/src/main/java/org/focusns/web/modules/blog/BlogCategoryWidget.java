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
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.blog.BlogCategoryService;
import org.focusns.service.core.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class BlogCategoryWidget {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping("/category-list")
    public String list(@WidgetAttribute Project project, Model model) {
        //
        Long projectId = project.getId();
        List<BlogCategory> blogCategories = blogCategoryService.getBlogCategories(projectId);
        model.addAttribute("blogCategories", blogCategories);
        //
        return "modules/blog/category-list";
    }

    @RequestMapping("/category-edit")
    public String doEdit(@RequestParam(required = false) Long categoryId, @WidgetAttribute ProjectUser projectUser,
            @WidgetAttribute Project project, Model model) {
        //
        Long projectId = project.getId();
        List<BlogCategory> blogCategories = blogCategoryService.getBlogCategories(projectId);
        model.addAttribute("blogCategories", blogCategories);
        //
        BlogCategory blogCategory = new BlogCategory();
        if (categoryId == null) {
            blogCategory.setProjectId(projectId);
            blogCategory.setCreatedById(projectUser.getId());
        } else {
            blogCategory = blogCategoryService.getBlogCategory(categoryId);
        }
        model.addAttribute("blogCategory", blogCategory);
        //
        return "modules/blog/category-edit";
    }

    @RequestMapping({ "/category-create", "/category-modify" })
    public void doModify(BlogCategory blogCategory) {
        Navigator.get().withAttribute("blogCategory", blogCategory);
        if (blogCategory.getId() > 0) {
            blogCategoryService.modifyBlogCategory(blogCategory);
            Navigator.get().navigateTo("category-modified");
        } else {
            blogCategoryService.createBlogCategory(blogCategory);
            Navigator.get().navigateTo("category-created");
        }
    }

    @RequestMapping("/category-remove")
    public void doRemove(@RequestParam Long id) {
        BlogCategory blogCategory = blogCategoryService.getBlogCategory(id);
        blogCategoryService.removeBlogCategory(blogCategory);
        Navigator.get().withAttribute("blogCategory", blogCategory).navigateTo("category-removed");
    }

}
