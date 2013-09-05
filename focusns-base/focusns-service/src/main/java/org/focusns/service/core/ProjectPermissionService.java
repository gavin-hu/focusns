package org.focusns.service.core;

import org.focusns.model.core.ProjectPermission;
import org.focusns.model.core.ProjectRole;

import java.util.List;
import java.util.Map;

public interface ProjectPermissionService {

    ProjectPermission getProjectPermissioin(long permissionId);

    void createProjectPermission(ProjectPermission permission);

    void modifyProjectPermission(ProjectPermission permission);

    void removeProjectPermission(ProjectPermission permission);

    /**
     * 获取给定 空间ID 的所有权限
     *
     * @param projectId
     * @return 列表返回
     */
    List<ProjectPermission> listProjectPermissions(long projectId);

    /**
     * 获取给定 空间ID 的所有权限
     *
     * @param projectId
     * @return 哈希返回
     */
    Map<ProjectRole, List<ProjectPermission>> listProjectPermissionsAsMap(long projectId);

}
