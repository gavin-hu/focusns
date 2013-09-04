package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectPermission;

import java.util.List;

public interface ProjectPermissionDao extends BaseDao<ProjectPermission> {

    List<ProjectPermission> selectByProjectId(long projectId);

}
