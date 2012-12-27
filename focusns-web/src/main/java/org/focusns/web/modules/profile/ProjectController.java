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

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.focusns.model.core.Project;
import org.focusns.service.core.ProjectService;
import org.focusns.web.utils.RuntimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/{projectCode}/{featureCode}")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @RequestMapping("/project/logo-link.jpg")
    public @ResponseBody byte[] linkLogo(@PathVariable("projectCode") String projectCode,
            @PathVariable("featureCode") String featureCode, HttpSession session) throws IOException {
        //
        Project project = projectService.getProject(projectCode);
        File target = RuntimeHelper.getInstance().getProjectLogo(project);
        return FileCopyUtils.copyToByteArray(target);
    }
    
}