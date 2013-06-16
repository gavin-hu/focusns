package org.focusns.common.web.page.engine.widget;

import org.focusns.common.web.page.config.WidgetConfig;

public interface WidgetCache {

    void put(WidgetConfig widgetConfig, Object value);

    Object get(WidgetConfig widgetConfig);

}
