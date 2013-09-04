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
import org.focusns.model.common.Page;
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

    @Override
    public boolean existProjectUser(ProjectUser projectUser) {

        return false;
    }

    public ProjectUser getProjectUser(String username) {
        ProjectUser user = projectUserDao.selectByUsername(username);
        if (user!=null && user.getProjectId() > 0) {
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
        ProjectUser dbProjectUser = projectUserDao.selectByUsername(projectUser.getEmail());
        if(dbProjectUser!=null) {
            throw new ServiceException(ServiceExceptionCode.PROJECT_USER_ALREADY_EXIST, "用户已经存在！");
        }
        //
        //String md5Password = DigestUtils.md5DigestAsHex(projectUser.getPassword().getBytes());
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
                throw new ServiceException(ServiceExceptionCode.PASSWORD_MISS_MATCH, "新老密码不匹配！");
            }
        } else {
            projectUserDao.update(projectUser);
        }
    }

    @Override
    public Page<ProjectUser> fetchPage(Page<ProjectUser> page) {
        page = projectUserDao.fetch(page);
        for(ProjectUser projectUser : page.getResults()) {
            fillProjectUser(projectUser);
        }
        return page;
    }

    public void removeProjectUser(ProjectUser user) {
        projectUserDao.delete(user.getId());
    }

    protected ProjectUser fillProjectUser(ProjectUser projectUser) {
        if(projectUser==null) {
            return projectUser;
        }
        //
        if(projectUser.getProject()==null && projectUser.getProjectId()>0) {
            Project project = projectDao.select(projectUser.getProjectId());
            projectUser.setProject(project);
        }
        //
        return projectUser;
    }

}
