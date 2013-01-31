
package org.focusns.service.core;

import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectLink;

import java.util.List;

public interface ProjectLinkService {

    ProjectLink getProjectLink(long id);
    
    void createProjectLink(ProjectLink link);
    
    void modifyProjectLink(ProjectLink link);
    
    void removeProjectLink(ProjectLink link);

    void removeProjectLink(long fromProjectId, long toProjectId);

    ProjectLink getProjectLink(long fromProjectId, long toProjectId);

    Page<ProjectLink> fetchPageByToProjectId(Page<ProjectLink> page, long toProjectId);

    Page<ProjectLink> fetchPageByFromProjectId(Page<ProjectLink> page, long fromProjectId);

}
