package org.focusns.web.modules.admin;

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
import org.focusns.model.core.Project;
import org.focusns.service.blog.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class BlogAdminWidget {
    
    @Autowired
    private BlogCategoryService categoryService;
    
    public String editCategory(Map<String, Object> model, Project project) {
        //
        List<BlogCategory> blogCategories = categoryService.getBlogCategories(project.getId());
        model.put("blogCategories", blogCategories);
        //
        return "modules/admin/blog/category-edit";
    }
    
    public String editPost(Map<String, Object> model, @RequestParam Long id, Project project) {
        //
        List<BlogCategory> blogCategories = categoryService.getBlogCategories(project.getId());
        model.put("blogCategories", blogCategories);
        //
        return "modules/admin/blog/post-edit";
    }
    
}
