

package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectRole;

public interface ProjectRoleDao extends BaseDao<ProjectRole> {

    void insertAuthority(long projectId, long roleId, long authorityId);

    void deleteAuthority(long projectId, long roleId, long authorityId);

}
