package org.focusns.web.console;

/*
 * #%L
 * FocusSNS Web
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import org.focusns.model.core.ProjectCategory;
import org.focusns.service.core.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class SiteWidget {

    @Autowired
    private ProjectCategoryService categoryService;

    public String menuItems(Map<String, Object> model) {
        List<ProjectCategory> categories = categoryService.listCategories(true);
        model.put("categories", categories);
        return "console/menu-items";
    }
    
    public String menuItem(long id, Map<String, Object> model) {
       ProjectCategory category = new ProjectCategory();
        if(id>0) {
            category = categoryService.getCategory(id);
        }
        model.put("category", category);
        return "console/menu-item";
    }
    
}
