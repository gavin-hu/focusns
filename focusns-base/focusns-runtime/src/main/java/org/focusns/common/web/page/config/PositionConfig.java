package org.focusns.common.web.page.config;

import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PositionConfig {

    private PageConfig pageConfig;
    //
    private String name;
    private Map<String, WidgetConfig> widgetConfigMap = new LinkedHashMap<String, WidgetConfig>();

    public PositionConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    public PageConfig getPageConfig() {
        return pageConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WidgetConfig> getOrderedWidgetConfigList() {
        List<WidgetConfig> widgetConfigList = new ArrayList<WidgetConfig>();
        for(WidgetConfig widgetConfig : widgetConfigMap.values()) {
            widgetConfigList.add(widgetConfig);
        }
        OrderComparator.sort(widgetConfigList);
        //
        return widgetConfigList;
    }

    public Map<String, WidgetConfig> getWidgetConfigMap() {
        return widgetConfigMap;
    }

    public void addWidgetConfig(WidgetConfig widgetConfig) {
        this.widgetConfigMap.put(widgetConfig.getId(), widgetConfig);
    }

}
