package org.focusns.web.portal.config;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import org.focusns.web.widget.config.WidgetConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageConfig {

    private String path;
    private String mode;
    private String category = "default";

	private Map<String, List<WidgetConfig>> positionMap = new HashMap<String, List<WidgetConfig>>();
	
	public PageConfig() {
	}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if(category!=null) {
            this.category = category;
        }
    }

    public Map<String, List<WidgetConfig>> getWidgetConfigMap() {
		return positionMap;
	}
	
	public List<WidgetConfig> getWidgetConfigList(String position) {
		return positionMap.get(position);
	}
	
	public void addWidgetConfigList(String position, List<WidgetConfig> widgetConfigList) {
		this.positionMap.put(position, widgetConfigList);
	}
	
}
