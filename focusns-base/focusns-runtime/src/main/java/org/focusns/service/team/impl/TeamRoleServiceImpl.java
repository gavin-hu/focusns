package org.focusns.service.team.impl;

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
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.dao.team.TeamRoleDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.model.team.TeamRole;
import org.focusns.service.team.TeamRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamRoleServiceImpl implements TeamRoleService {

    @Autowired
    private TeamRoleDao teamRoleDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;

    public TeamRole getTeamRole(long roleId) {
        TeamRole teamRole = teamRoleDao.select(roleId);
        return fillTeamRole(teamRole);
    }

    public void createTeamRole(TeamRole role) {
        teamRoleDao.insert(role);
        fillTeamRole(role);
    }

    public void modifyTeamRole(TeamRole role) {
        teamRoleDao.update(role);
        fillTeamRole(role);
    }

    public void removeTeamRole(TeamRole role) {
        teamRoleDao.delete(role.getId());
        fillTeamRole(role);
    }

    @Override
    public List<TeamRole> listTeamRoles(long projectId) {
        List<TeamRole> teamRoles = teamRoleDao.selectByProjectId(projectId);
        for(TeamRole teamRole : teamRoles) {
            fillTeamRole(teamRole);
        }
        return teamRoles;
    }

    protected TeamRole fillTeamRole(TeamRole teamRole) {
        if(teamRole==null) {
            return teamRole;
        }
        //
        if(teamRole.getProject()==null && teamRole.getProjectId()>0) {
            Project project = projectDao.select(teamRole.getProjectId());
            teamRole.setProject(project);
        }
        if(teamRole.getCreatedBy()==null && teamRole.getCreatedById()>0) {
            ProjectUser createdBy = projectUserDao.select(teamRole.getCreatedById());
            teamRole.setCreatedBy(createdBy);
        }
        if(teamRole.getModifiedBy()==null && teamRole.getModifiedById()>0) {
            ProjectUser modifiedBy = projectUserDao.select(teamRole.getModifiedById());
            teamRole.setModifiedBy(modifiedBy);
        }
        //
        return teamRole;
    }
}
