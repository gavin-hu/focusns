package org.focusns.dao.core.impl;

import java.util.List;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectLogoDao;
import org.focusns.model.core.ProjectLogo;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectLogoDaoImpl extends MyBatisBaseDao<ProjectLogo> 
    implements ProjectLogoDao {

    public List<ProjectLogo> selectList(long projectId) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), projectId);
    }

}
