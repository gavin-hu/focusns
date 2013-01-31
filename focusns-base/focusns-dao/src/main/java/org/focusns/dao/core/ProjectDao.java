
package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.core.Project;

public interface ProjectDao extends BaseDao<Project> {

	Project selectByCode(String code);

}
