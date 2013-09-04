package org.focusns.service.core;

import org.focusns.model.core.ProjectPermission;

import java.util.List;

public interface ProjectPermissionService {

    ProjectPermission getProjectPermissioin(long permissionId);

    void createProjectPermission(ProjectPermission permission);

    void modifyProjectPermission(ProjectPermission permission);

    void removeProjectPermission(ProjectPermission permission);

    List<ProjectPermission> listProjectPermissions(long projectId);

}
