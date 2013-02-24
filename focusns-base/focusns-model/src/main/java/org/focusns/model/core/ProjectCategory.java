package org.focusns.model.core;

import org.focusns.model.common.Id;

public class ProjectCategory extends Id {

	private String code;
    private String label;
    private int level; // 排序字段
    private boolean enabled = true;
    private boolean _private = false;
	
	public ProjectCategory() {
	}
    
    public ProjectCategory(long categoryId) {
        setId(categoryId);
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
    
    public boolean isPrivate() {
		return _private;
	}
    
    public void setPrivate(boolean _private) {
		this._private = _private;
	}
    
}
