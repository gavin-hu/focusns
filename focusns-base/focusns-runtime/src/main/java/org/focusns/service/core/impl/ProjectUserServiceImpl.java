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

import org.focusns.common.exception.ServiceException;
import org.focusns.common.exception.ServiceExceptionCode;
import org.focusns.dao.core.ProjectCategoryDao;
import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@Transactional
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private ProjectCategoryDao projectCategoryDao;

    public ProjectUser getProjectUser(String username) {
        ProjectUser user = projectUserDao.selectByUsername(username);
        if (user.getProjectId() > 0) {
            Project project = projectDao.select(user.getProjectId());
            user.setProject(project);
            //
            ProjectCategory projectCategory = projectCategoryDao.select(project.getCategoryId());
            project.setCategory(projectCategory);
        }
        //
        return user;
    }

    public ProjectUser getProjectUser(long userId) {
        return projectUserDao.select(userId);
    }

    @Override
    public void enableProjectUser(ProjectUser projectUser) {

    }

    public void createProjectUser(ProjectUser projectUser) {
        //
        String md5Password = DigestUtils.md5DigestAsHex(projectUser.getPassword().getBytes());
        projectUser.setPassword(md5Password);
        projectUser.setUsername(projectUser.getEmail());
        projectUser.setCreatedAt(new Date());
        //
        projectUserDao.insert(projectUser);
    }

    public void modifyProjectUser(ProjectUser projectUser) {
        //
        if(StringUtils.hasText(projectUser.getOldPassword())
                && StringUtils.hasText(projectUser.getNewPassword())) {
            String oldHashedPassword = DigestUtils.md5DigestAsHex(projectUser.getOldPassword().getBytes());
            String newHashedPassword = DigestUtils.md5DigestAsHex(projectUser.getNewPassword().getBytes());
            //
            if(oldHashedPassword.equals(projectUser.getPassword())) {
                projectUser.setPassword(newHashedPassword);
                projectUserDao.update(projectUser);
            } else {
                throw new ServiceException(ServiceExceptionCode.PASSWORD_MISS_MATCH, "新旧密码不匹配！");
            }
        } else {
            projectUserDao.update(projectUser);
        }
    }

    public void removeProjectUser(ProjectUser user) {
        projectUserDao.delete(user.getId());
    }

    public void assignRole(long projectId, long userId, long roleId) {
        projectUserDao.insertRole(projectId, userId, roleId);
    }

    public void unassignRole(long projectId, long userId, long roleId) {
        projectUserDao.deleteRole(projectId, userId, roleId);
    }

}
