package org.focusns.service.core.impl;

/*
 * #%L
 * FocusSNS Runtime
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

import java.util.Date;

import org.focusns.dao.core.ProjectCategoryDao;
import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectFeatureDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectFeature;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
@Transactional
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private ProjectFeatureDao projectFeatureDao;
    @Autowired
    private ProjectCategoryDao projectCategoryDao;

    public ProjectUser getUser(String username) {
        ProjectUser user = projectUserDao.selectByUsername(username);
        if (user.getProjectId() > 0) {
            Project project = projectDao.select(user.getProjectId());
            user.setProject(project);
        }
        //
        return user;
    }

    public ProjectUser getUser(long userId) {
        return projectUserDao.select(userId);
    }

    public void createUser(ProjectUser user) {
        //
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        //
        projectUserDao.insert(user);
        //
        ProjectCategory category = projectCategoryDao.selectByCode("people");
        //
        Date now = new Date();
        Project project = new Project();
        project.setCode(user.getUsername());
        project.setTitle(user.getUsername());
        project.setDescription("");
        project.setCategoryId(category.getId());
        project.setCreatedAt(now);
        project.setModifiedAt(now);
        project.setCreatedById(user.getId());
        project.setModifiedById(user.getId());
        //
        projectDao.insert(project);
        user.setProjectId(project.getId());
        projectUserDao.update(user);
        //
        ProjectFeature profileFeature = new ProjectFeature();
        profileFeature.setLabel("主页");
        profileFeature.setCode("profile");
        profileFeature.setLevel(5);
        profileFeature.setProjectId(project.getId());
        //
        ProjectFeature blogFeature = new ProjectFeature();
        blogFeature.setLabel("日志");
        blogFeature.setCode("blog");
        blogFeature.setLevel(10);
        blogFeature.setProjectId(project.getId());
        //
        ProjectFeature photoFeature = new ProjectFeature();
        photoFeature.setLabel("相册");
        photoFeature.setCode("photo");
        photoFeature.setLevel(15);
        photoFeature.setProjectId(project.getId());
        //
        ProjectFeature teamFeature = new ProjectFeature();
        teamFeature.setLabel("人脉");
        teamFeature.setCode("team");
        teamFeature.setLevel(20);
        teamFeature.setProjectId(project.getId());
        //
        ProjectFeature msgFeature = new ProjectFeature();
        msgFeature.setLabel("私信");
        msgFeature.setCode("msg");
        msgFeature.setLevel(25);
        msgFeature.setProjectId(project.getId());
        //
        ProjectFeature settingFeature = new ProjectFeature();
        settingFeature.setLabel("设置");
        settingFeature.setCode("admin");
        settingFeature.setLevel(30);
        settingFeature.setProjectId(project.getId());
        //
        projectFeatureDao.insert(profileFeature);
        projectFeatureDao.insert(blogFeature);
        projectFeatureDao.insert(photoFeature);
        projectFeatureDao.insert(teamFeature);
        projectFeatureDao.insert(msgFeature);
        projectFeatureDao.insert(settingFeature);
    }

    public void modifyUser(ProjectUser user) {
        projectUserDao.update(user);
    }

    public void removeUser(ProjectUser user) {
        projectUserDao.delete(user.getId());
    }

    public void assignRole(long projectId, long userId, long roleId) {
        projectUserDao.insertRole(projectId, userId, roleId);
    }

    public void unassignRole(long projectId, long userId, long roleId) {
        projectUserDao.deleteRole(projectId, userId, roleId);
    }

}
