package org.focusns.dao.core;

import java.util.List;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectAttribute;

public interface ProjectAttributeDao extends BaseDao<ProjectAttribute> {

	List<ProjectAttribute> selectByProjectId(long projectId);
	
}
