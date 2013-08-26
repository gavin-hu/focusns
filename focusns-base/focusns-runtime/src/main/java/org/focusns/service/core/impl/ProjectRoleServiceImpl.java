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

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectRoleDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectRole;
import org.focusns.service.core.ProjectRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectRoleServiceImpl implements ProjectRoleService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectRoleDao projectRoleDao;

    public ProjectRole getProjectRole(long roleId) {
        ProjectRole projectRole = projectRoleDao.select(roleId);
        return projectRole;
    }

    public void createProjectRole(ProjectRole projectRole) {
        this.projectRoleDao.insert(projectRole);
        fillProjectRole(projectRole);
    }

    public void modifyProjectRole(ProjectRole projectRole) {
        this.projectRoleDao.update(projectRole);
        fillProjectRole(projectRole);
    }

    public void removeProjectRole(ProjectRole projectRole) {
        this.projectRoleDao.delete(projectRole.getId());
        fillProjectRole(projectRole);
    }

    @Override
    public List<ProjectRole> listProjectRoles(long projectId) {
        List<ProjectRole> projectRoles = this.projectRoleDao.selectByProjectId(projectId);
        for(ProjectRole projectRole : projectRoles) {
            fillProjectRole(projectRole);
        }
        //
        return projectRoles;
    }

    public void authorizeAuthority(long projectId, long roleId, long authorityId) {
        this.projectRoleDao.insertAuthority(projectId, roleId, authorityId);
    }

    public void deauthorizeAuthority(long projectId, long roleId, long authorityId) {
        this.projectRoleDao.deleteAuthority(projectId, roleId, authorityId);
    }

    protected ProjectRole fillProjectRole(ProjectRole projectRole) {
        if(projectRole==null) {
            return projectRole;
        }
        //
        if(projectRole.getProject()==null && projectRole.getProjectId()>0) {
            Project project = projectDao.select(projectRole.getProjectId());
            projectRole.setProject(project);
        }
        //
        return projectRole;
    }
}
