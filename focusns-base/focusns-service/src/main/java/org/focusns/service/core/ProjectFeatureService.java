
package org.focusns.service.core;

import org.focusns.model.core.ProjectFeature;

import java.util.List;

public interface ProjectFeatureService {
	
	void createProjectFeature(ProjectFeature projectFeature);
	
	List<ProjectFeature> getProjectFeatures(long projectId);

	ProjectFeature getProjectFeature(long projectId, String featureCode);
	
}
