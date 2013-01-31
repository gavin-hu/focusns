
package org.focusns.model.msg;

import org.focusns.model.common.Id;

import java.util.Date;

public class Message extends Id {

    private String title;
    private String content;
    private Date readAt;
    private Date createAt;
    //
    private long projectId;
    private long createById;
    private long toProjectId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReadAt() {
        return readAt;
    }

    public void setReadAt(Date readAt) {
        this.readAt = readAt;
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

    public long getToProjectId() {
        return toProjectId;
    }

    public void setToProjectId(long toProjectId) {
        this.toProjectId = toProjectId;
    }
}
