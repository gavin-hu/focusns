
package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.ProjectLogo;

import java.util.List;

public interface ProjectLogoDao extends BaseDao<ProjectLogo> {

    List<ProjectLogo> selectList(long projectId);
    
}
