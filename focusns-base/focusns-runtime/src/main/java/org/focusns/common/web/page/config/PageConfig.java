package org.focusns.common.web.page.config;

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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PageConfig {

    private String path;
    private String layout;
    private String authority;
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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
        for (PositionConfig positionConfig : positionConfigMap.values()) {
            widgetConfig = positionConfig.getWidgetConfigMap().get(widgetId);
            if (widgetConfig != null) {
                break;
            }
        }
        return widgetConfig;
    }

}
