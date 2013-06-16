package org.focusns.web.console.site;

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

import org.focusns.common.web.widget.stereotype.Widget;
import org.focusns.model.core.ProjectCategory;
import org.focusns.service.core.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Widget
@RequestMapping("/console/site")
public class MenuWidget {

    @Autowired
    private ProjectCategoryService categoryService;

    @RequestMapping("/menu-list")
    public String doList(Model model) {
        List<ProjectCategory> categories = categoryService.listCategories(true);
        model.addAttribute("categories", categories);
        //
        return "console/site/menu-list";
    }

    @RequestMapping("/menu-edit")
    public String doEdit(@RequestParam(required = false) Long id, Model model) {
        ProjectCategory category = new ProjectCategory();
        if (id != null) {
            category = categoryService.getCategory(id);
        }
        model.addAttribute("category", category);
        //
        return "console/site/menu-edit";
    }

    @RequestMapping("/menu-modify")
    public void modify(ProjectCategory category, Model model) {
        if (category.getId() > 0) {
            categoryService.modifyCategory(category);
        } else {
            categoryService.createCategory(category);
        }
    }
}
