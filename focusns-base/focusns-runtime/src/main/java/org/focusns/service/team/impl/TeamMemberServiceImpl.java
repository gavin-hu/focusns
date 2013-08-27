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
import org.focusns.dao.core.ProjectRoleDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.dao.team.TeamMemberDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectRole;
import org.focusns.model.core.ProjectUser;
import org.focusns.model.team.TeamMember;
import org.focusns.service.team.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    private ProjectRoleDao projectRoleDao;


    public TeamMember getTeamMember(long memberId) {
        TeamMember teamMember = teamMemberDao.select(memberId);
        return fillTeamMember(teamMember);
    }

    public void createTeamMember(TeamMember teamMember) {
        //
        Date now = new Date();
        if(teamMember.getCreatedAt()==null) {
            teamMember.setCreatedAt(now);
        }
        if(teamMember.getModifiedAt()==null) {
            teamMember.setModifiedAt(now);
        }
        //
        this.teamMemberDao.insert(teamMember);
        fillTeamMember(teamMember);
    }

    public void modifyTeamMember(TeamMember teamMember) {
        //
        teamMember.setModifiedAt(new Date());
        //
        this.teamMemberDao.update(teamMember);
        fillTeamMember(teamMember);
    }

    public void removeTeamMember(TeamMember teamMember) {
        this.teamMemberDao.delete(teamMember.getId());
        fillTeamMember(teamMember);
    }

    @Override
    public Page<TeamMember> fetchPage(Page<TeamMember> page, long projectId) {
        return fetchPage(page, projectId, 0);
    }

    @Override
    public Page<TeamMember> fetchPage(Page<TeamMember> page, long projectId, long roleId) {
        page = teamMemberDao.fetchByProjectId(page, projectId, roleId);
        for(TeamMember teamMember : page.getResults()) {
            fillTeamMember(teamMember);
        }
        return page;
    }

    @Override
    public Page<TeamMember> fetchPagePotentially(Page<TeamMember> page) {
        page = teamMemberDao.fetchPotentially(page);
        for(TeamMember teamMember : page.getResults()) {
            fillTeamMember(teamMember);
        }
        return page;
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
            ProjectUser createBy = projectUserDao.selectWithProject(teamMember.getCreatedById());
            teamMember.setCreatedBy(createBy);
        }
        if(teamMember.getModifiedBy()==null && teamMember.getModifiedById()>0) {
            ProjectUser modifiedBy = projectUserDao.selectWithProject(teamMember.getModifiedById());
            teamMember.setModifiedBy(modifiedBy);
        }
        if(teamMember.getUser()==null && teamMember.getUserId()>0) {
            ProjectUser projectUser = projectUserDao.selectWithProject(teamMember.getUserId());
            teamMember.setUser(projectUser);
        }
        if(teamMember.getRole()==null && teamMember.getRoleId()>0) {
            ProjectRole projectRole = projectRoleDao.select(teamMember.getRoleId());
            teamMember.setRole(projectRole);
        }
        //
        return teamMember;
    }

}
