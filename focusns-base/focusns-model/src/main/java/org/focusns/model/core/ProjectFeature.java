
package org.focusns.model.core;

import org.focusns.model.common.Id;

public class ProjectFeature extends Id {

	private String code;
	private String label;
	private int level; // 排序字段
	private boolean enabled;
	//
	private long projectId;


    public ProjectFeature() {
    }

    public ProjectFeature(long projectId) {
        this.projectId = projectId;
    }

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

}
