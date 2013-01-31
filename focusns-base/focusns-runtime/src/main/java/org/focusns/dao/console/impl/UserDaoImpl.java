

package org.focusns.dao.console.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.console.UserDao;
import org.focusns.model.console.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl extends MyBatisBaseDao<User>
        implements UserDao {
    @Override
    public void assign(long userId, long roleId) {
        //
        Map parameter = getParameter(userId, roleId);
        //
        getSqlSession().insert(NAMESPACE.concat(".assign"), parameter);
    }

    @Override
    public void unassign(long userId, long roleId) {
        //
        Map parameter = getParameter(userId, roleId);
        //
        getSqlSession().delete(NAMESPACE.concat(".unassign"), parameter);
    }

    private Map getParameter(long userId, long roleId) {
        //
        Map parameter = new HashMap();
        parameter.put("userId", userId);
        parameter.put("roleId", roleId);
        //
        return parameter;
    }
}
