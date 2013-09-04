package org.focusns.dao.core.impl;

import org.focusns.common.dao.mybatis.MyBatisDaoSupport;
import org.focusns.dao.core.ProjectPermissionDao;
import org.focusns.model.core.ProjectPermission;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectPermissionDaoImpl extends MyBatisDaoSupport<ProjectPermission> implements ProjectPermissionDao {

    @Override
    public List<ProjectPermission> selectByProjectId(long projectId) {
        //
        return selectList("selectByProjectId", projectId);
    }
}
