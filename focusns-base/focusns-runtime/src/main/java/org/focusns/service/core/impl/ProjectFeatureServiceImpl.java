package org.focusns.service.core.impl;

import java.util.List;

import org.focusns.dao.core.ProjectFeatureDao;
import org.focusns.model.core.ProjectFeature;
import org.focusns.service.core.ProjectFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectFeatureServiceImpl implements ProjectFeatureService {

	@Autowired
	private ProjectFeatureDao projectFeatureDao;
	
	public void createProjectFeature(ProjectFeature projectFeature) {
		projectFeatureDao.insert(projectFeature);
	}
	
	public List<ProjectFeature> getProjectFeatures(long projectId) {
		return projectFeatureDao.selectByProjectId(projectId);
	}

	public ProjectFeature getProjectFeature(long projectId, String featureCode) {
		return projectFeatureDao.selectByProjectIdAndCode(projectId, featureCode);
	}

}
