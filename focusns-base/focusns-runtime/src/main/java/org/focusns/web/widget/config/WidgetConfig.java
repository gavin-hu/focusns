/*
 * Copyright (C) 2011-2013 FocusSNS.
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
package org.focusns.web.widget.config;

import java.util.HashMap;
import java.util.Map;

public class WidgetConfig {

	private String className;
    private String methodName;
    private Map<String, Object> preferences = new HashMap<String, Object>();
	
	public WidgetConfig(String target) {
        this.className = target.substring(0, target.indexOf("@"));
        this.methodName = target.substring(target.indexOf("@")+1);
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getMethodName() {
		return methodName;
	}

    public Map<String, Object> getPreferences() {
        return preferences;
    }

    public void setPreference(Map<String, Object> preferences) {
        this.preferences = preferences;
    }
}
