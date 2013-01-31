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


import org.focusns.dao.team.TeamRoleDao;
import org.focusns.model.team.TeamRole;
import org.focusns.service.team.TeamRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamRoleServiceImpl implements TeamRoleService {

    @Autowired
    private TeamRoleDao teamRoleDao;

    @Override
    public TeamRole getTeamRole(long roleId) {
        return teamRoleDao.select(roleId);
    }

    @Override
    public void createTeamRole(TeamRole role) {
        teamRoleDao.insert(role);
    }

    @Override
    public void modifyTeamRole(TeamRole role) {
        teamRoleDao.update(role);
    }

    @Override
    public void removeTeamRole(TeamRole role) {
        teamRoleDao.delete(role.getId());
    }
}
