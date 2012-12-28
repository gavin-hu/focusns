/*
 * Copyright (C) 2012 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
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
