
package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectCategory;

import java.util.List;

public interface ProjectCategoryDao extends BaseDao<ProjectCategory> {
	
	ProjectCategory selectByCode(String code);

    List<ProjectCategory> selectList(boolean isPublic);

}
