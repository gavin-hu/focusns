package org.focusns.dao.core;

import java.util.List;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectLogo;

public interface ProjectLogoDao extends BaseDao<ProjectLogo> {

    List<ProjectLogo> selectList(long projectId);
    
}
