package org.focusns.dao.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.ProjectUser;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectUserDaoImpl extends MyBatisBaseDao<ProjectUser>
	implements ProjectUserDao {

	public ProjectUser selectByUsername(String username) {
		return getSqlSession()
				.selectOne(NAMESPACE.concat(".selectByUsername"), username);
	}

    public void insertRole(long projectId, long userId, long roleId) {
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        parameter.put("userId", userId);
        parameter.put("roleId", roleId);
        //
        getSqlSession().insert(NAMESPACE.concat(".insertRole"), parameter);
    }

    public void deleteRole(long projectId, long userId, long roleId) {
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        parameter.put("userId", userId);
        parameter.put("roleId", roleId);
        //
        getSqlSession().delete(NAMESPACE.concat(".deleteRole"), roleId);
    }

}
