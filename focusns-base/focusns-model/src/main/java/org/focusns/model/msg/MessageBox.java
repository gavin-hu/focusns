
package org.focusns.model.msg;

import org.focusns.model.common.Id;

import java.util.Date;

public class MessageBox extends Id {

    public static String TYPE_IN = "IN";
    public static String TYPE_SENT = "SENT";
    
    private String label;
    private String type;
    private Date createAt;
    //
    private long projectId;
    private long createById;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
