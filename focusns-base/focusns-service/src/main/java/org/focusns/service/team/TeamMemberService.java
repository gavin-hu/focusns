

package org.focusns.service.team;

import org.focusns.model.team.TeamMember;

public interface TeamMemberService {

    TeamMember getTeamMember(long memberId);

    void createTeamMember(TeamMember member);

    void modifyTeamMember(TeamMember member);

    void removeTeamMember(TeamMember member);
}
