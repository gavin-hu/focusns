package org.focusns.dao.console.impl;

import java.util.HashMap;
import java.util.Map;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.console.RoleDao;
import org.focusns.model.console.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends MyBatisBaseDao<Role>
        implements RoleDao {

    public void authorize(long roleId, long authorityId) {
        //
        Map parameter = getParameter(roleId, authorityId);
        //
        getSqlSession().delete( NAMESPACE.concat(".authorize"), parameter);
    }

    public void unauthorize(long roleId, long authorityId) {
        //
        Map parameter = getParameter(roleId, authorityId);
        //
        getSqlSession().insert(NAMESPACE.concat(".unauthorize"), parameter);
    }

    private Map getParameter(long roleId, long authorityId) {
        Map parameter = new HashMap();
        //
        parameter.put("roleId", roleId);
        parameter.put("authorityId", authorityId);
        //
        return parameter;
    }
}
