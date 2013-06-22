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

import org.focusns.common.image.ImageUtils;
import org.focusns.common.image.Rectangle;
import org.focusns.common.web.WebUtils;
import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectLink;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.common.TempStorageService;
import org.focusns.service.common.helper.CoordinateHelper;
import org.focusns.service.core.ProjectLinkService;
import org.focusns.service.core.ProjectUserService;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.Constraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/project")
public class ProjectUserWidget {

    @Autowired@Qualifier("localTempStorageService")
    private TempStorageService storageService;
    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private ProjectLinkService projectLinkService;

    @RequestMapping("/user-edit")
    @Constraints({ Constraint.PROJECT_NOT_NULL, Constraint.PROJECT_USER_NOT_NULL })
    public String doEdit(@WidgetAttribute Project project, @WidgetAttribute ProjectUser projectUser, Model model) throws IOException {
        //
        Object[] avatarCoordinates = CoordinateHelper.getAvatarCoordinates(projectUser);
        if(storageService.existsTempResource(avatarCoordinates)) {
            model.addAttribute("tempExists", true);
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

    @RequestMapping("/user-avatar/download")
    public ResponseEntity<byte[]> doAvatar(@RequestParam Long userId, @RequestParam(required = false) Boolean temp,
            @RequestParam(required = false) Integer width, @RequestParam(required = false) Integer height) throws IOException {
        //
        InputStream inputStream = null;
        ProjectUser projectUser = projectUserService.getUser(userId);
        Object[] avatarCoordinates = CoordinateHelper.getAvatarCoordinates(projectUser);
        if(temp!=null && temp.booleanValue()) {
            inputStream = storageService.loadTempResource(avatarCoordinates);
        } else if(width==null || height==null) {
            inputStream = storageService.loadResource(avatarCoordinates);
        } else {
            Object size = width + "x" + height;
            inputStream = storageService.loadSizedResource(size, avatarCoordinates);
        }
        return WebUtils.getResponseEntity(FileCopyUtils.copyToByteArray(inputStream), MediaType.IMAGE_PNG);
    }

    @RequestMapping("/user-avatar/upload")
    public void doUpload(@RequestParam Long projectId, @RequestParam Long userId, MultipartFile file)
            throws IOException {
        //
        ProjectUser projectUser = projectUserService.getUser(userId);
        Object[] avatarCoordinates = CoordinateHelper.getAvatarCoordinates(projectUser);
        storageService.persistTempResource(file.getInputStream(), avatarCoordinates);
        //
        Navigator.get().navigateTo("avatar-uploaded");
    }

    @RequestMapping("/user-avatar/crop")
    public void doCrop(@RequestParam Long projectId, @RequestParam Long userId, Rectangle rectangle) throws IOException {
        //
        ProjectUser projectUser = projectUserService.getUser(userId);
        Object[] avatarCoordinates = CoordinateHelper.getAvatarCoordinates(projectUser);
        //
        InputStream tempInputStream = storageService.loadTempResource(avatarCoordinates);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageUtils.crop(tempInputStream, baos, rectangle);
        // bridge from ByteArrayOutputStream
        InputStream targetInputStream = new ByteArrayInputStream(baos.toByteArray());
        storageService.persistResource(targetInputStream, avatarCoordinates);
        storageService.cleanSizedResource(avatarCoordinates);
        //
        Navigator.get().navigateTo("avatar-cropped");
    }
}
