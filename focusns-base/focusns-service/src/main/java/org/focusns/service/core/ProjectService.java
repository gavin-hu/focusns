
package org.focusns.service.core;

import org.focusns.model.core.Project;

public interface ProjectService {
	
    Project getProject(long id);
    
	Project getProject(String code);

	void createProject(Project project);

	void removeProject(Project project);

    void modifyProject(Project project);
	
}
