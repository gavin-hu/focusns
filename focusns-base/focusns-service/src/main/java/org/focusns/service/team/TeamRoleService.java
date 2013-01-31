

package org.focusns.service.team;

import org.focusns.model.team.TeamRole;

public interface TeamRoleService {

    TeamRole getTeamRole(long roleId);

    void createTeamRole(TeamRole role);

    void modifyTeamRole(TeamRole role);

    void removeTeamRole(TeamRole role);

}
