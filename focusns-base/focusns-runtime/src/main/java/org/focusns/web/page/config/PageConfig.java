
package org.focusns.web.page.config;

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
