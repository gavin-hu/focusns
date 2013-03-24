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

import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.focusns.web.helper.Coordinate;
import org.focusns.web.helper.Rectangle;
import org.focusns.web.helper.RuntimeHelper;
import org.focusns.web.widget.Constraint;
import org.focusns.web.widget.annotation.Constraints;
import org.focusns.web.widget.annotation.WidgetAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/project")
public class ProjectUserWidget {

    @Autowired
    private ProjectUserService projectUserService;

    @RequestMapping("/user-edit")
    @Constraints({Constraint.PROJECT_REQUIRED, Constraint.PROJECT_USER_REQUIRED})
    public String doEdit(@WidgetAttribute Project project,
                         @WidgetAttribute ProjectUser projectUser, Model model) {
        //
        Coordinate avatarCoordinate = getAvatarCoordinate(project.getId(), projectUser.getId());
        if(RuntimeHelper.isTempFileExists(avatarCoordinate)) {
            model.addAttribute("hasTempFile", true);
        }
        //
        model.addAttribute("project", project);
        model.addAttribute("projectUser", projectUser);
        //
        return "modules/profile/user-edit";
    }

    @RequestMapping("/user-view")
    @Constraints(Constraint.PROJECT_REQUIRED)
    public String doView(@WidgetAttribute Project project, Model model) {
        //
        ProjectUser projectUser = projectUserService.getUser(project.getCreateById());
        model.addAttribute("projectUser", projectUser);
        //
        return "modules/profile/user-view";
    }

    @RequestMapping("/user-avatar")
    public @ResponseBody byte[] doAvatar(@RequestParam Long projectId,
                                         @RequestParam Long userId) throws IOException {
        //
        Coordinate avatarCoordinate = getAvatarCoordinate(projectId, userId);
        File tempFile = RuntimeHelper.getTempFile(avatarCoordinate);
        return FileCopyUtils.copyToByteArray(tempFile);
    }

    @RequestMapping("/user-avatar/upload")
    public void doUpload(@RequestParam Long projectId,
                         @RequestParam Long userId, MultipartFile file) throws IOException {
        //
        Coordinate avatarCoordinate = getAvatarCoordinate(projectId, userId);
        RuntimeHelper.setTempFile(avatarCoordinate, file.getInputStream());
    }

    @RequestMapping("/user-avatar/crop")
    public void doCrop(@RequestParam Long projectId,
                       @RequestParam Long userId, Rectangle rectangle) throws IOException {
        //
        Coordinate avatarCoordinate = getAvatarCoordinate(projectId, userId);
        File tempFile = RuntimeHelper.getTempFile(avatarCoordinate);
        File targetFile = RuntimeHelper.getTargetFile(avatarCoordinate);
        RuntimeHelper.cropThumbnail(tempFile, targetFile, rectangle);
    }

    private Coordinate getAvatarCoordinate(Object projectId, Object userId) {
        return new Coordinate(projectId, "profile", "avatar", userId);
    }
}
