package org.focusns.dao.team;

import java.util.Date;

import org.focusns.dao.AbstractDaoTest;
import org.focusns.model.team.TeamMember;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class TeamMemberDaoTest extends AbstractDaoTest {

    @Autowired
    private TeamMemberDao teamMemberDao;

    @Test
    public void testInsert() {
        TeamMember member = new TeamMember();
        member.setCreateAt(new Date());
        member.setModifyAt(new Date());
        member.setUserId(1);
        member.setRoleId(1);
        member.setProjectId(1);
        //
        this.teamMemberDao.insert(member);
    }
}
