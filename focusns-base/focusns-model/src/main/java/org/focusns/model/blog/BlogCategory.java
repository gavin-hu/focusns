
package org.focusns.model.blog;

import org.focusns.model.common.Id;

import java.util.Date;

public class BlogCategory extends Id {
    
    private String label;
    private Date createAt;
    //
    private long createById;
    private long projectId;

    public BlogCategory() {
    }

    public BlogCategory(long projectId, String name) {
        this.label = name;
        this.projectId = projectId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public long getCreateById() {
        return createById;
    }

    public void setCreateById(long createById) {
        this.createById = createById;
    }
}
