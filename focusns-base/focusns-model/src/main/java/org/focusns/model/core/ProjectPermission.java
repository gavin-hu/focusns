package org.focusns.model.core;

import org.focusns.model.common.Id;


public class ProjectPermission extends Id {

    private long projectId;
    private long projectRoleId;
    private long projectAuthorityId;
    //
    private Project project;
    private ProjectRole projectRole;
    private ProjectAuthority projectAuthority;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getProjectRoleId() {
        return projectRoleId;
    }

    public void setProjectRoleId(long projectRoleId) {
        this.projectRoleId = projectRoleId;
    }

    public long getProjectAuthorityId() {
        return projectAuthorityId;
    }

    public void setProjectAuthorityId(long projectAuthorityId) {
        this.projectAuthorityId = projectAuthorityId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectRole getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(ProjectRole projectRole) {
        this.projectRole = projectRole;
    }

    public ProjectAuthority getProjectAuthority() {
        return projectAuthority;
    }

    public void setProjectAuthority(ProjectAuthority projectAuthority) {
        this.projectAuthority = projectAuthority;
    }

}
