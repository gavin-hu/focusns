
package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectAttribute;

import java.util.List;

public interface ProjectAttributeDao extends BaseDao<ProjectAttribute> {

	List<ProjectAttribute> selectByProjectId(long projectId);
	
}
