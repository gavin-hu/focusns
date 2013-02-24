package org.focusns.model.forum;

import java.util.Date;

import org.focusns.model.common.Id;

public class ForumCategory extends Id {
    
    private String label;
    private Date createAt;
    //
    private long projectId;
    private long createById;

    public ForumCategory() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getCreateById() {
        return createById;
    }

    public void setCreateById(long createById) {
        this.createById = createById;
    }
}
