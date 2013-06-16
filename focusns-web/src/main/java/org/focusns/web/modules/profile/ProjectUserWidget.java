package org.focusns.web.modules.profile;

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

import java.io.File;
import java.io.IOException;

import org.focusns.common.image.ImageUtils;
import org.focusns.common.image.Rectangle;
import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectLink;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectLinkService;
import org.focusns.service.core.ProjectUserService;
import org.focusns.web.helper.Coordinate;
import org.focusns.web.helper.RuntimeHelper;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.Constraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/project")
public class ProjectUserWidget implements ResourceLoaderAware {

    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private ProjectLinkService projectLinkService;

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping("/user-edit")
    @Constraints({ Constraint.PROJECT_NOT_NULL, Constraint.PROJECT_USER_NOT_NULL })
    public String doEdit(@WidgetAttribute Project project, @WidgetAttribute ProjectUser projectUser, Model model) {
        //
        Coordinate avatarCoordinate = getAvatarCoordinate(project.getId(), projectUser.getId());
        if (RuntimeHelper.isTempFileExists(avatarCoordinate)) {
            model.addAttribute("hasTempFile", true);
        }
        //
        if (RuntimeHelper.isTargetFileExists(avatarCoordinate)) {
            model.addAttribute("hasTargetFile", true);
        }
        //
        model.addAttribute("project", project);
        model.addAttribute("projectUser", projectUser);
        //
        return "modules/profile/user-edit";
    }

    @RequestMapping("/user-view")
    @Constraints({ Constraint.PROJECT_NOT_NULL })
    public String doView(@WidgetAttribute(required = false) ProjectUser projectUser, @WidgetAttribute Project project,
            Model model) {
        //
        ProjectUser dbUser = projectUserService.getUser(project.getCreatedById());
        model.addAttribute("projectUser", dbUser);
        model.addAttribute("project", project);
        //
        if (projectUser != null) {
            ProjectLink projectLink = projectLinkService.getProjectLink(projectUser.getProjectId(), project.getId());
            model.addAttribute("projectLink", projectLink);
            model.addAttribute("fromProject", projectUser.getProject());
            model.addAttribute("toProject", project);
        }
        //
        return "modules/profile/user-view";
    }

    @RequestMapping("/user-avatar")
    public @ResponseBody
    byte[] doAvatar(@RequestParam Long projectId, @RequestParam Long userId,
            @RequestParam(required = false) Boolean isTempFile, @RequestParam(required = false) Integer dimension)
            throws IOException {
        //
        Coordinate avatarCoordinate = getAvatarCoordinate(projectId, userId);
        if (isTempFile != null && isTempFile.booleanValue()) {
            File tempFile = RuntimeHelper.getTempFile(avatarCoordinate);
            return FileCopyUtils.copyToByteArray(tempFile);
        } else {
            File targetFile = RuntimeHelper.getTargetFile(avatarCoordinate);
            if (targetFile == null || !targetFile.exists()) {
                targetFile = getDefaultAvatarFile("default");
            }
            //
            if (dimension != null) {
                avatarCoordinate.setDimension(dimension);
                File resizedTargetFile = RuntimeHelper.getTargetFile(avatarCoordinate);
                if (!resizedTargetFile.exists()) {
                    ImageUtils.resize(targetFile, resizedTargetFile, dimension, dimension, "PNG");
                }
                //
                targetFile = resizedTargetFile;
            }
            //
            return FileCopyUtils.copyToByteArray(targetFile);
        }
    }

    @RequestMapping("/user-avatar/upload")
    public void doUpload(@RequestParam Long projectId, @RequestParam Long userId, MultipartFile file)
            throws IOException {
        //
        Coordinate avatarCoordinate = getAvatarCoordinate(projectId, userId);
        RuntimeHelper.setTempFile(avatarCoordinate, file.getInputStream());
        //
        Navigator.get().navigateTo("avatar-uploaded");
    }

    @RequestMapping("/user-avatar/crop")
    public void doCrop(@RequestParam Long projectId, @RequestParam Long userId, Rectangle rectangle) throws IOException {
        //
        Coordinate avatarCoordinate = getAvatarCoordinate(projectId, userId);
        RuntimeHelper.cleanTargetFile(avatarCoordinate);
        //
        File tempFile = RuntimeHelper.getTempFile(avatarCoordinate);
        File targetFile = RuntimeHelper.getTargetFile(avatarCoordinate);
        ImageUtils.crop(tempFile, targetFile, rectangle);
        //
        Navigator.get().navigateTo("avatar-cropped");
    }

    private Coordinate getAvatarCoordinate(Object projectId, Object userId) {
        return new Coordinate(projectId, "profile", "avatar", userId);
    }

    private File getDefaultAvatarFile(String themeName) throws IOException {
        return resourceLoader.getResource(String.format("/WEB-INF/themes/%s/img/person_65.png", themeName)).getFile();
    }

}
