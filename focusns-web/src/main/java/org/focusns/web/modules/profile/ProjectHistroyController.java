/*
 * Copyright (C) 2012 FocuSNS.
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
package org.focusns.web.modules.profile;

import org.focusns.model.core.ProjectHistroy;
import org.focusns.service.core.ProjectHistroyService;
import org.focusns.web.utils.ActionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{projectCode}/{featureCode}")
public class ProjectHistroyController {
    
    @Autowired
    private ProjectHistroyService projectHistroyService;
    
    @RequestMapping("/project/histroy-edit.action")
    public String editHistroy(
            @PathVariable("projectCode") String projectCode,
            @PathVariable("featureCode") String featureCode,
            @RequestParam String action, ProjectHistroy histroy) {
        //
        if(ActionHelper.isCreate(action)) {
            projectHistroyService.createProjectHistroy(histroy);
        } 
        //
        return "redirect:/" + projectCode + "/" + featureCode;
    }
    
}
