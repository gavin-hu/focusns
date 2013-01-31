
package org.focusns.dao.core.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectCategoryDao;
import org.focusns.model.core.ProjectCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectCategoryDaoImpl extends MyBatisBaseDao<ProjectCategory> implements ProjectCategoryDao {

	public ProjectCategory selectByCode(String code) {
		return getSqlSession().selectOne(NAMESPACE.concat(".selectByCode"), code);
	}

    public List<ProjectCategory> selectList(boolean isPublic) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), !isPublic);
    }
    
}
