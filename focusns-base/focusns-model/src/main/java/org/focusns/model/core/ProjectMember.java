package org.focusns.model.core;

import org.focusns.model.common.Id;

import java.util.Date;


public class ProjectMember extends Id {
    //
    private long projectId;
    private long projectUserId;
    private long projectRoleId;
    //
    private Date createdAt;
    private Date modifiedAt;
    //
    private long createdById;
    private long modifiedById;
    //
    private Project project;
    private ProjectUser projectUser;
    private ProjectRole projectRole;
    //
    private ProjectUser createdBy;
    private ProjectUser modifiedBy;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getProjectUserId() {
        return projectUserId;
    }

    public void setProjectUserId(long projectUserId) {
        this.projectUserId = projectUserId;
    }

    public long getProjectRoleId() {
        return projectRoleId;
    }

    public void setProjectRoleId(long projectRoleId) {
        this.projectRoleId = projectRoleId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(long createdById) {
        this.createdById = createdById;
    }

    public long getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(long modifiedById) {
        this.modifiedById = modifiedById;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectUser getProjectUser() {
        return projectUser;
    }

    public void setProjectUser(ProjectUser projectUser) {
        this.projectUser = projectUser;
    }

    public ProjectRole getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(ProjectRole projectRole) {
        this.projectRole = projectRole;
    }

    public ProjectUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(ProjectUser createdBy) {
        this.createdBy = createdBy;
    }

    public ProjectUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(ProjectUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
