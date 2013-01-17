/*
 * Copyright (C) 2011-2013 FocusSNS.
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

import org.focusns.model.core.ProjectLogo;
import org.focusns.web.helpers.RuntimeHelper;
import org.focusns.service.core.ProjectLogoService;
import org.focusns.service.core.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/project")
public class ProjectLogoController {

   /* @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectLogoService projectLogoService;
    
    @RequestMapping("/logo")
    public @ResponseBody byte[] linkLogo(@PathVariable long logoId) throws IOException {
        //
        ProjectLogo logo = projectLogoService.getProjectLogo(logoId);
        //
        File target = RuntimeHelper.getInstance().getProjectLogo(logo);
        return FileCopyUtils.copyToByteArray(target);
    }*/

}
