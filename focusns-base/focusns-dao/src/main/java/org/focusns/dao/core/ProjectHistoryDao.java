
package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectHistory;

import java.util.List;

public interface ProjectHistoryDao extends BaseDao<ProjectHistory> {

    List<ProjectHistory> selectByParentId(long parentId);

    Page<ProjectHistory> fetchByProjectId(Page<ProjectHistory> page, long projectId);
    
}
