package org.focusns.web.site;

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

import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectFeature;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectFeatureService;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.annotation.Constraints;
import org.focusns.web.widget.annotation.WidgetAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site")
public class UserMenuWidget {

    @Autowired
    private ProjectFeatureService featureService;

    @RequestMapping("/menu-user")
    @Constraints(Constraint.PROJECT_USER_REQUIRED)
    public String doShow(@WidgetAttribute ProjectUser projectUser, Model model) {
        //
        Long projectId = projectUser.getProjectId();
        List<ProjectFeature> features = featureService.getProjectFeatures(projectId);
        model.addAttribute("features", features);
        //
        return "site/menu-user";
    }
}
