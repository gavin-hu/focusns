
package org.focusns.model.core;

import org.focusns.model.common.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectHistory extends Id {
    
    private String content;
    private Date createAt;
    private long targetId;
    private String targetType;
    //
    private long parentId;
    private long projectId;
    private long createById;

    //
    private Project project;
    private ProjectUser createBy;
    private Object target;
    //
    private List<ProjectHistory> children = new ArrayList<ProjectHistory>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(ProjectUser createBy) {
        this.createBy = createBy;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public List<ProjectHistory> getChildren() {
        return children;
    }

    public void setChildren(List<ProjectHistory> children) {
        this.children = children;
    }
}
