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
package org.focusns.web.modules.admin;

import org.focusns.model.common.Rectangle;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAttribute;
import org.focusns.model.core.ProjectFeature;
import org.focusns.model.core.ProjectLogo;
import org.focusns.runtime.RuntimeHelper;
import org.focusns.service.core.ProjectAttributeService;
import org.focusns.service.core.ProjectLogoService;
import org.focusns.service.core.ProjectService;
import org.focusns.web.utils.WebRequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminProfileController {
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectLogoService projectLogoService;
    @Autowired
    private ProjectAttributeService projectAttributeService;
    
    @RequestMapping("/project/logo/upload")
    public String uploadLogo(MultipartFile file, WebRequest webRequest) throws IOException {
        Project project = WebRequestHelper.getProject(webRequest);
        ProjectFeature feature = WebRequestHelper.getProjectFeature(webRequest);
        //
        RuntimeHelper.getInstance().storeTmpProjectLogo(file.getInputStream(), webRequest.getSessionId());
        //
        return "redirect:/"+project.getCode()+"/"+feature.getCode()+"/profile/logo-edit";
    }
    
    @RequestMapping("/project/logo/crop")
    public String cropLogo(Rectangle rectangle, WebRequest webRequest) throws IOException {
        //
        Project project = WebRequestHelper.getProject(webRequest);
        ProjectFeature feature = WebRequestHelper.getProjectFeature(webRequest);
        //
        File tmp = RuntimeHelper.getInstance().getTmpProjectLogo(webRequest.getSessionId());
        projectLogoService.cropProjectLogoImage(project, tmp, rectangle);
        //
        return "redirect:/"+project.getCode()+"/"+feature.getCode()+"/profile/logo-edit";
    }
    
    @RequestMapping("/project/logo/tmp")
    public @ResponseBody byte[] linkLogoTemp(WebRequest webRequest) throws IOException {
        //
        File temp = RuntimeHelper.getInstance().getTmpProjectLogo(webRequest.getSessionId());
        return FileCopyUtils.copyToByteArray(temp);
    }

    @RequestMapping("/project/logo/{logoId}")
    public @ResponseBody byte[] linkLogo(@PathVariable long logoId, WebRequest webRequest) throws IOException {
        //
        ProjectLogo projectLogo = projectLogoService.getProjectLogo(logoId);
        File imageFile =  projectLogoService.loadProjectLogoImage(projectLogo);
        return FileCopyUtils.copyToByteArray(imageFile);
    }
    
    @RequestMapping("/project/edit")
    public String editProject(Project project, WebRequest webRequest) {
        ProjectFeature feature = WebRequestHelper.getProjectFeature(webRequest);
        //
        Project dbProject = projectService.getProject(project.getId());
        dbProject.setCode(project.getCode());
        dbProject.setTitle(project.getTitle());
        dbProject.setDescription(project.getDescription());
        projectService.modifyProject(dbProject);
        //
        return "redirect:/"+dbProject.getCode()+"/"+feature.getCode()+"/profile/project-edit";
    }
    
    @RequestMapping("/project/attribute/edit")
    public String editAttribute(ProjectAttribute attribute, WebRequest webRequest) {
        //
        Project project = WebRequestHelper.getProject(webRequest);
        ProjectFeature feature = WebRequestHelper.getProjectFeature(webRequest);
        //
        if(attribute.getId()>0) {
            projectAttributeService.modifyProjectAttribute(attribute);
        } else {
            projectAttributeService.createProjectAttribute(attribute);
        }
        //
        return "redirect:/"+project.getCode()+"/"+feature.getCode()+"/profile/attribute-edit";
    }
}
