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
package org.focusns.web.modules.admin;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAttribute;
import org.focusns.service.core.ProjectAttributeService;
import org.focusns.web.utils.RuntimeHelper;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

@Widget
public class AdminProfileWidget {
    
    @Autowired
    private ProjectAttributeService attributeService;
    
    public String editLogo(Map<String, Object> model,
            @Bind(value="id", scope = Bind.Scope.SESSION) String sessionId,
            @Bind(value="WEBROOT", scope = Bind.Scope.APPLICATION) String webRoot,
            @Bind(value="project", scope = Bind.Scope.REQUEST) Project project) {
        //
        File dest = RuntimeHelper.getInstance().getTmpProjectLogo(project);
        model.put("hasTmpLogo", dest.canRead());
        //
        File target = RuntimeHelper.getInstance().getProjectLogo(project);
        model.put("hasLogo", target.canRead());
        //
        return "admin/profile/logo-edit";
    }
    
    public String editProject(@Bind(value="project", scope = Bind.Scope.REQUEST) Project project) {
        return "admin/profile/project-edit";
    }
    
    public String editAttribute(Map<String, Object> model,
            @Bind(value="project", scope = Bind.Scope.REQUEST) Project project) {
        //
        List<ProjectAttribute> attributes = attributeService.getProjectAttributes(project.getId());
        model.put("attributes", attributes);
        //
        return "admin/profile/attribute-edit";
    }
    
}
