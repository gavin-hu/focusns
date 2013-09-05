package org.focusns.web.form.core;

import org.focusns.model.core.ProjectPermission;

import java.util.ArrayList;
import java.util.List;

public class ProjectPermissionForm {

    private ProjectPermission projectPermission;
    private List<ProjectPermission> projectPermissions = new ArrayList<ProjectPermission>();

    public ProjectPermission getProjectPermission() {
        return projectPermission;
    }

    public void setProjectPermission(ProjectPermission projectPermission) {
        this.projectPermission = projectPermission;
    }

    public List<ProjectPermission> getProjectPermissions() {
        return projectPermissions;
    }

    public void setProjectPermissions(List<ProjectPermission> projectPermissions) {
        this.projectPermissions = projectPermissions;
    }

}
