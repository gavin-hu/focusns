package org.focusns.service.core.impl;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import org.focusns.dao.core.ProjectRoleDao;
import org.focusns.model.core.ProjectRole;
import org.focusns.service.core.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectRoleServiceImpl implements ProjectRoleService {

    @Autowired
    private ProjectRoleDao projectRoleDao;

    public ProjectRole getProjectRole(long roleId) {
        return projectRoleDao.select(roleId);
    }

    public void createProjectRole(ProjectRole role) {
        this.projectRoleDao.insert(role);
    }

    public void modifyProjectRole(ProjectRole role) {
        this.projectRoleDao.update(role);
    }

    public void removeProjectRole(ProjectRole role) {
        this.projectRoleDao.delete(role.getId());
    }

    public void authorizeAuthority(long projectId, long roleId, long authorityId) {
        this.projectRoleDao.insertAuthority(projectId, roleId, authorityId);
    }

    public void deauthorizeAuthority(long projectId, long roleId, long authorityId) {
        this.projectRoleDao.deleteAuthority(projectId, roleId, authorityId);
    }
}
