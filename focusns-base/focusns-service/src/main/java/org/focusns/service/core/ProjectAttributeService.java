package org.focusns.service.core;

import org.focusns.model.core.ProjectAttribute;

import java.util.List;

public interface ProjectAttributeService {
    
    void createProjectAttribute(ProjectAttribute attribute);
    
    void modifyProjectAttribute(ProjectAttribute attribute);
    
    void removeProjectAttribute(ProjectAttribute attribute);
    
    List<ProjectAttribute> getProjectAttributes(long projectId);
    
}
