
package org.focusns.dao.core.impl;

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.core.ProjectFeatureDao;
import org.focusns.model.core.ProjectFeature;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectFeatureDaoImpl extends MyBatisBaseDao<ProjectFeature> 
	implements ProjectFeatureDao {

	public List<ProjectFeature> selectByProjectId(long projectId) {
		return getSqlSession()
				.selectList(NAMESPACE.concat(".selectByProjectId"), projectId);
	}

	public ProjectFeature selectByProjectIdAndCode(long projectId, String code) {
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("projectId", String.valueOf(projectId));
		parameter.put("code", code);
		return getSqlSession()
				.selectOne(NAMESPACE.concat(".selectByProjectIdAndCode"), parameter);
	}
	
}
