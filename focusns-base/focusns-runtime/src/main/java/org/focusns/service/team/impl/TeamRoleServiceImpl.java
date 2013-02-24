package org.focusns.service.team.impl;

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

    public TeamRole getTeamRole(long roleId) {
        return teamRoleDao.select(roleId);
    }

    public void createTeamRole(TeamRole role) {
        teamRoleDao.insert(role);
    }

    public void modifyTeamRole(TeamRole role) {
        teamRoleDao.update(role);
    }

    public void removeTeamRole(TeamRole role) {
        teamRoleDao.delete(role.getId());
    }
}
