
package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectUser;

public interface ProjectUserDao extends BaseDao<ProjectUser> {

	ProjectUser selectByUsername(String username);

    void insertRole(long projectId, long userId, long roleId);

    void deleteRole(long projectId, long userId, long roleId);

}
