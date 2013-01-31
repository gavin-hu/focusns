
package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectFeature;

import java.util.List;

public interface ProjectFeatureDao extends BaseDao<ProjectFeature> {
	
	List<ProjectFeature> selectByProjectId(long projectId);

	ProjectFeature selectByProjectIdAndCode(long projectId, String code);
	
}
