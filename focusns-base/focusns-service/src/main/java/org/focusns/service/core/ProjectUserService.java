package org.focusns.service.core;

import org.focusns.model.core.ProjectUser;

public interface ProjectUserService {

    ProjectUser getUser(String username);

    ProjectUser getUser(long userId);
	
	void createUser(ProjectUser user);
	
	void removeUser(ProjectUser user);

    void modifyUser(ProjectUser user);

    void assignRole(long projectId, long userId, long roleId);

    void unassignRole(long projectId, long userId, long roleId);
	
}
