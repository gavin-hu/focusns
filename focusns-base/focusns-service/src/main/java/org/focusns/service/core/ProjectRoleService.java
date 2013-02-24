package org.focusns.service.core;

import org.focusns.model.core.ProjectRole;

public interface ProjectRoleService {

    ProjectRole getProjectRole(long roleId);

    void createProjectRole(ProjectRole role);

    void modifyProjectRole(ProjectRole role);

    void removeProjectRole(ProjectRole role);

    void authorizeAuthority(long projectId, long roleId, long authorityId);

    void deauthorizeAuthority(long projectId, long roleId, long authorityId);

}
