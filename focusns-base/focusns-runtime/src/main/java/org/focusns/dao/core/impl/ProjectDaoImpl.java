
package org.focusns.dao.core.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectDao;
import org.focusns.model.core.Project;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl extends MyBatisBaseDao<Project> implements ProjectDao {

	public Project selectByCode(String code) {
		return getSqlSession().selectOne(NAMESPACE.concat(".selectByCode"), code);
	}

}
