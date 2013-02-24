package org.focusns.dao.core;

import java.util.List;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectCategory;

public interface ProjectCategoryDao extends BaseDao<ProjectCategory> {
	
	ProjectCategory selectByCode(String code);

    List<ProjectCategory> selectList(boolean isPublic);

}
