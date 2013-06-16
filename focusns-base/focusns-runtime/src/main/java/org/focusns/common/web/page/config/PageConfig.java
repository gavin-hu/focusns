package org.focusns.common.web.page.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PageConfig {

    private String path;
    private String layout;
    private Map<String, String> parameters = new HashMap<String, String>();
    private Map<String, PositionConfig> positionConfigMap = new HashMap<String, PositionConfig>();
    //
    private Map<String, WidgetConfig> pageWidgetConfigMap = new HashMap<String, WidgetConfig>();

    public PageConfig(String path, String layout) {
        this.path = path;
        this.layout = layout;
    }

    public String getPath() {
        return path;
    }

    public String getLayout() {
        return layout;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void addPositionConfig(PositionConfig positionConfig) {
        this.positionConfigMap.put(positionConfig.getName(), positionConfig);
    }

    public Map<String, PositionConfig> getPositionConfigMap() {
        return positionConfigMap;
    }

    public WidgetConfig getWidgetConfigById(String widgetId) {
        WidgetConfig widgetConfig = null;
        for(PositionConfig positionConfig : positionConfigMap.values()) {
            widgetConfig = positionConfig.getWidgetConfigMap().get(widgetId);
            if(widgetConfig!=null) {
                break;
            }
        }
        return widgetConfig;
    }

}
