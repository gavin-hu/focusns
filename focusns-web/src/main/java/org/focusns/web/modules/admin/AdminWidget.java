package org.focusns.web.modules.admin;

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


import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectFeature;
import org.focusns.service.core.ProjectFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class AdminWidget {
    
    @Autowired
    private ProjectFeatureService featureService;
    
    public String menu(Project project, Map<String, Object> model) {
         //
        List<ProjectFeature> features = featureService.getProjectFeatures(project.getId());
        model.put("features", features);
        //
        return "modules/admin/menu";
    }
    
    public String summary() {
        //
        return "modules/admin/summary";
    }
     
}
