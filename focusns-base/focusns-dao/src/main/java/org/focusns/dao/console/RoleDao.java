package org.focusns.dao.console;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.console.Role;

public interface RoleDao extends BaseDao<Role> {

    void authorize(long roleId, long authorityId);

    void unauthorize(long roleId, long authorityId);
}
