package org.focusns.dao.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectRoleDao;
import org.focusns.model.core.ProjectRole;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRoleDaoImpl extends MyBatisBaseDao<ProjectRole> implements ProjectRoleDao {

    public void insertAuthority(long projectId, long roleId, long authorityId) {
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        parameter.put("roleId", roleId);
        parameter.put("authorityId", authorityId);
        //
        getSqlSession().insert(NAMESPACE.concat(".insertAuthority"), parameter);
    }

    public void deleteAuthority(long projectId, long roleId, long authorityId) {
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        parameter.put("roleId", roleId);
        parameter.put("authorityId", authorityId);
        //
        getSqlSession().delete(NAMESPACE.concat(".deleteAuthority"), parameter);
    }
}
