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

package org.focusns.service.team.impl;

import org.focusns.dao.team.TeamMemberDao;
import org.focusns.model.team.TeamMember;
import org.focusns.service.team.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberDao teamMemberDao;

    @Override
    public TeamMember getTeamMember(long memberId) {
        return teamMemberDao.select(memberId);
    }

    @Override
    public void createTeamMember(TeamMember member) {
        this.teamMemberDao.insert(member);
    }

    @Override
    public void modifyTeamMember(TeamMember member) {
        this.teamMemberDao.update(member);
    }

    @Override
    public void removeTeamMember(TeamMember member) {
        this.teamMemberDao.delete(member.getId());
    }
}
