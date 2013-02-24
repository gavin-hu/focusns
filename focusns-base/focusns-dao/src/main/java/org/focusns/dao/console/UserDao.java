package org.focusns.dao.console;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.console.User;

public interface UserDao extends BaseDao<User> {

    void assign(long userId, long roleId);

    void unassign(long userId, long roleId);
}
