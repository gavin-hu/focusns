package org.focusns.web.widget.engine;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.config.WidgetConfig;

public interface WidgetFilter {

    boolean doFilter(WidgetRequest request, WidgetResponse response, WidgetConfig widgetConfig) throws Exception;
    
}
