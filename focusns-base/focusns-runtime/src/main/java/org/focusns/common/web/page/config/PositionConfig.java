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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.OrderComparator;

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
        for (WidgetConfig widgetConfig : widgetConfigMap.values()) {
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
