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
}
