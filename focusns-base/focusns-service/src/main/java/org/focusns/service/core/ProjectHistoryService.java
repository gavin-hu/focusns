package org.focusns.service.core;

import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectHistory;

public interface ProjectHistoryService {

    void createProjectHistory(ProjectHistory history);
    
    void modifyProjectHistory(ProjectHistory history);
    
    void removeProjectHistory(ProjectHistory history);
    
    Page<ProjectHistory> fetchPage(Page<ProjectHistory> page, long projectId);
    
}
