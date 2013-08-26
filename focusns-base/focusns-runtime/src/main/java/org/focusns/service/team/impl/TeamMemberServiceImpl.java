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
import org.focusns.dao.team.TeamMemberDao;
import org.focusns.dao.team.TeamRoleDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.model.team.TeamMember;
import org.focusns.model.team.TeamRole;
import org.focusns.service.team.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberDao teamMemberDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private TeamRoleDao teamRoleDao;

    public TeamMember getTeamMember(long memberId) {
        return teamMemberDao.select(memberId);
    }

    public void createTeamMember(TeamMember member) {
        this.teamMemberDao.insert(member);
    }

    public void modifyTeamMember(TeamMember member) {
        this.teamMemberDao.update(member);
    }

    public void removeTeamMember(TeamMember member) {
        this.teamMemberDao.delete(member.getId());
    }

    @Override
    public Page<TeamMember> fetchPage(Page<TeamMember> page, long projectId) {
        return teamMemberDao.fetchByProjectId(page, projectId);
    }

    protected TeamMember fillTeamMember(TeamMember teamMember) {
        if(teamMember==null) {
            return teamMember;
        }
        //
        if(teamMember.getProject()==null && teamMember.getProjectId()>0) {
            Project project = projectDao.select(teamMember.getProjectId());
            teamMember.setProject(project);
        }
        if(teamMember.getCreatedBy()==null && teamMember.getCreatedById()>0) {
            ProjectUser createBy = projectUserDao.select(teamMember.getCreatedById());
            teamMember.setCreatedBy(createBy);
        }
        if(teamMember.getModifiedBy()==null && teamMember.getModifiedById()>0) {
            ProjectUser modifiedBy = projectUserDao.select(teamMember.getModifiedById());
            teamMember.setModifiedBy(modifiedBy);
        }
        /*if(teamMember.getRole()==null && teamMember.getRoleId()>0) {
            TeamRole teamRole = teamRoleDao.select(teamMember.getRoleId());
            teamMember.setRole(teamRole);
        }*/
        //
        return teamMember;
    }

}
