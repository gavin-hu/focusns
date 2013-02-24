package org.focusns.web.modules.setting;

import java.io.File;
import java.io.IOException;

import org.focusns.model.common.Rectangle;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAttribute;
import org.focusns.model.core.ProjectFeature;
import org.focusns.service.core.ProjectAttributeService;
import org.focusns.service.core.ProjectService;
import org.focusns.web.helper.ApplicationHelper;
import org.focusns.web.helper.WebRequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/setting")
public class ProfileSettingController {
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectAttributeService projectAttributeService;
    
    @RequestMapping("/project/logo/upload")
    public String uploadLogo(MultipartFile file, WebRequest webRequest) throws IOException {
        Project project = WebRequestHelper.getProject(webRequest);
        ProjectFeature feature = WebRequestHelper.getProjectFeature(webRequest);
        //
        ApplicationHelper.getInstance().storeTmpProjectLogo(file.getInputStream(), webRequest.getSessionId());
        //
        return "redirect:/"+project.getCode()+"/"+feature.getCode()+"/profile/logo-edit";
    }
    
    @RequestMapping("/project/logo/crop")
    public String cropLogo(Rectangle rectangle, WebRequest webRequest) throws IOException {
        //
        Project project = WebRequestHelper.getProject(webRequest);
        ProjectFeature feature = WebRequestHelper.getProjectFeature(webRequest);
        //
        File target = ApplicationHelper.getInstance().getProjectLogo(project.getId());
        File original = ApplicationHelper.getInstance().getTmpProjectLogo(webRequest.getSessionId());
        ApplicationHelper.getInstance().cropProjectLogo(original, target, rectangle);
        //
        return "redirect:/"+project.getCode()+"/"+feature.getCode()+"/profile/logo-edit";
    }
    
    @RequestMapping("/project/logo/tmp")
    public @ResponseBody byte[] linkLogoTemp(WebRequest webRequest) throws IOException {
        //
        File temp = ApplicationHelper.getInstance().getTmpProjectLogo(webRequest.getSessionId());
        return FileCopyUtils.copyToByteArray(temp);
    }

    @RequestMapping("/project/{projectId}/logo")
    public @ResponseBody byte[] linkLogo(@PathVariable long projectId) throws IOException {
        //
        File target = ApplicationHelper.getInstance().getProjectLogo(projectId);
        return FileCopyUtils.copyToByteArray(target);
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
