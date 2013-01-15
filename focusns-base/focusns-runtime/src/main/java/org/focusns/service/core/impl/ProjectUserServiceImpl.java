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
package org.focusns.service.core.impl;

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;
	
    public ProjectUser getUser(String username) {
        ProjectUser user = projectUserDao.selectByUsername(username);
        if(user.getProjectId()>0) {
            Project project = projectDao.select(user.getProjectId());
            user.setProject(project);
        }
        //
        return user;
    }

	public void createUser(ProjectUser user) {
		projectUserDao.insert(user);
	}

    public void modifyUser(ProjectUser user) {
       projectUserDao.update(user);
    }

    public void removeUser(ProjectUser user) {
		projectUserDao.delete(user.getId());
	}

    @Override
    public void assignRole(long projectId, long userId, long roleId) {
        projectUserDao.insertRole(projectId, userId, roleId);
    }

    @Override
    public void unassignRole(long projectId, long userId, long roleId) {
        projectUserDao.deleteRole(projectId, userId, roleId);
    }

}
