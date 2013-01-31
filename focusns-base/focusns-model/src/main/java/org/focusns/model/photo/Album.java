
package org.focusns.model.photo;

import org.focusns.model.common.Id;

import javax.xml.crypto.Data;
import java.util.Date;

public class Album extends Id {
    
    private String label;
    private Date createAt;
    //
    private long createById;
    private long projectId;

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

    public long getCreateById() {
        return createById;
    }

    public void setCreateById(long createById) {
        this.createById = createById;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
