package org.focusns.model.core;

import org.focusns.model.common.Id;

public class ProjectLogo extends Id {

    private boolean main;
    private long projectId;

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }
    
    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
    
}
