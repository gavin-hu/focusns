package org.focusns.service.team;

import org.focusns.model.team.TeamRole;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class TeamRoleServiceTest extends AbstractServiceTest {

    @Autowired
    private TeamRoleService teamRoleService;

    @Test
    public void testCreateTeamRole() {
        TeamRole teamRole = new TeamRole();
        teamRole.setLabel("好友");
        teamRole.setCreatedById(1);
        teamRole.setModifiedById(1);
        teamRole.setProjectId(1);
        //
        teamRoleService.createTeamRole(teamRole);
    }
}
