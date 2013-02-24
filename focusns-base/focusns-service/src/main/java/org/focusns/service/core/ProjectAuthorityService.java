package org.focusns.service.core;

import org.focusns.model.core.ProjectAuthority;

public interface ProjectAuthorityService {

    ProjectAuthority getProjectAuthority(long authorityId);

    void createProjectAuthority(ProjectAuthority authority);

    void modifyProjectAuthority(ProjectAuthority authority);

    void removeProjectAuthority(ProjectAuthority authority);
}
