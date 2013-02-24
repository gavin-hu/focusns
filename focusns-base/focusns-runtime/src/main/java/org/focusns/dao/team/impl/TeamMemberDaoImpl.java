package org.focusns.dao.team.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.team.TeamMemberDao;
import org.focusns.model.team.TeamMember;
import org.springframework.stereotype.Repository;

@Repository
public class TeamMemberDaoImpl extends MyBatisBaseDao<TeamMember> implements TeamMemberDao {
}
