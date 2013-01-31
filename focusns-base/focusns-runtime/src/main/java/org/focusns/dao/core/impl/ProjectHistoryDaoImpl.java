
package org.focusns.dao.core.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectHistoryDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectHistory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectHistoryDaoImpl extends MyBatisBaseDao<ProjectHistory>
    implements ProjectHistoryDao {

    public List<ProjectHistory> selectByParentId(long parentId) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectByParentId"), parentId);
    }

    public Page<ProjectHistory> fetchByProjectId(Page<ProjectHistory> page, long projectId) {
        //
        Map parameter = new HashMap();
        parameter.put("projectId", projectId);
        //
        return fetchPage(".fetchByProjectId", page, parameter);
    }
    
}
