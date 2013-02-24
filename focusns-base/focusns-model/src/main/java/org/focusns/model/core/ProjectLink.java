package org.focusns.model.core;

import org.focusns.model.common.Id;

public class ProjectLink extends Id {
    
    private long fromProjectId;
    private long toProjectId;
    private boolean mutual;

    private Project fromProject;
    private Project toProject;

    public ProjectLink() {
    }

    public long getFromProjectId() {
        return fromProjectId;
    }

    public void setFromProjectId(long fromProjectId) {
        this.fromProjectId = fromProjectId;
    }

    public long getToProjectId() {
        return toProjectId;
    }

    public void setToProjectId(long toProjectId) {
        this.toProjectId = toProjectId;
    }
    
    public boolean isMutual() {
        return mutual;
    }

    public void setMutual(boolean mutual) {
        this.mutual = mutual;
    }

    public Project getFromProject() {
        return fromProject;
    }

    public void setFromProject(Project fromProject) {
        this.fromProject = fromProject;
    }

    public Project getToProject() {
        return toProject;
    }

    public void setToProject(Project toProject) {
        this.toProject = toProject;
    }
}
