package org.focusns.dao.core;

import java.util.List;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectFeature;

public interface ProjectFeatureDao extends BaseDao<ProjectFeature> {
	
	List<ProjectFeature> selectByProjectId(long projectId);

	ProjectFeature selectByProjectIdAndCode(long projectId, String code);
	
}
