package org.focusns.web.page.config;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import org.focusns.web.widget.config.WidgetConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageConfig {

	private Map<String, List<WidgetConfig>> positionMap = new HashMap<String, List<WidgetConfig>>();
	
	public PageConfig() {
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
