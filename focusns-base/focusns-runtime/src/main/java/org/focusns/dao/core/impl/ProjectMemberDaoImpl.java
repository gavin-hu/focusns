package org.focusns.dao.core.impl;

import org.focusns.common.dao.mybatis.MyBatisDaoSupport;
import org.focusns.dao.core.ProjectMemberDao;
import org.focusns.model.core.ProjectMember;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectMemberDaoImpl extends MyBatisDaoSupport<ProjectMember> implements ProjectMemberDao {
}
