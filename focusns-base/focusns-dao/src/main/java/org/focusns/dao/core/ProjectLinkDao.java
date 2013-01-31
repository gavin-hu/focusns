
package org.focusns.dao.core;

import org.focusns.dao.common.BaseDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectLink;

import java.util.List;

public interface ProjectLinkDao extends BaseDao<ProjectLink> {

    ProjectLink selectByFromAndToProjectId(long fromProjectId, long toProjectId);

    void deleteByFromAndToProjectId(long fromProjectId, long toProjectId);

    Page<ProjectLink> fetchByToProjectId(Page<ProjectLink> page, Long toProjectId, Boolean mutual);

    Page<ProjectLink> fetchByFromProjectId(Page<ProjectLink> page, Long fromProjectId, Boolean mutual);

}
