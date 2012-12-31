/*
 * Copyright (C) 2012 FocusSNS.
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
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAttribute;
import org.focusns.service.core.ProjectAttributeService;
import org.focusns.service.core.ProjectService;
import org.focusns.runtime.RuntimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/{projectCode}/{featureCode}")
public class AdminProfileController {
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectAttributeService attributeService;
    
    @RequestMapping("/project/logo-upload.action")
    public String uploadLogo(@PathVariable("projectCode") String projectCode,
            @PathVariable("featureCode") String featureCode, MultipartFile file) throws IOException {
        //
        Project project = projectService.getProject(projectCode);
        RuntimeHelper.getInstance().storeTmpProjectLogo(file.getInputStream(), project);
        //
        return "redirect:/"+projectCode+"/"+featureCode+"/profile/logo-edit";
    }
    
    @RequestMapping("/project/logo-crop.action")
    public String cropLogo(@PathVariable("projectCode") String projectCode,
            @PathVariable("featureCode") String featureCode,
            String x, String y, String w, String h, HttpSession session) throws IOException {
        //
        Project project = projectService.getProject(projectCode);
        RuntimeHelper.getInstance().cropProjectLogo(project, x, y, w, h);
        //
        return "redirect:/"+projectCode+"/"+featureCode+"/profile/logo-edit";
    }
    
    @RequestMapping("/project/logo-link.tmp")
    public @ResponseBody byte[] linkLogo(@PathVariable("projectCode") String projectCode,
            @PathVariable("featureCode") String featureCode, HttpSession session) throws IOException {
        //
        Project project = projectService.getProject(projectCode);
        File dest = RuntimeHelper.getInstance().getTmpProjectLogo(project);
        return FileCopyUtils.copyToByteArray(dest);
    }
    
    @RequestMapping("/project-edit.action")
    public String editProject(Project project,
            @PathVariable("projectCode") String projectCode,
            @PathVariable("featureCode") String featureCode) {
        Project dbProject = projectService.getProject(project.getId());
        dbProject.setCode(project.getCode());
        dbProject.setTitle(project.getTitle());
        dbProject.setDescription(project.getDescription());
        projectService.modifyProject(dbProject);
        //
        return "redirect:/"+projectCode+"/"+featureCode+"/profile/project-edit";
    }
    
    @RequestMapping("/project/attribute-edit.action")
    public String editAttribute(ProjectAttribute attribute,
            @PathVariable("projectCode") String projectCode,
            @PathVariable("featureCode") String featureCode) {
        //
        if(attribute.getId()>0) {
            attributeService.modifyProjectAttribute(attribute);
        } else {
            attributeService.createProjectAttribute(attribute);
        }
        //
        return "redirect:/"+projectCode+"/"+featureCode+"/profile/attribute-edit";
    }
}
