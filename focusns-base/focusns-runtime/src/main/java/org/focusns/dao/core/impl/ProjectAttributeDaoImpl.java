package org.focusns.dao.core.impl;

import java.util.List;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectAttributeDao;
import org.focusns.model.core.ProjectAttribute;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectAttributeDaoImpl extends MyBatisBaseDao<ProjectAttribute> 
	implements ProjectAttributeDao {

	public List<ProjectAttribute> selectByProjectId(long projectId) {
		return getSqlSession().selectList(NAMESPACE.concat(".selectByProjectId"), projectId);
	}

}
