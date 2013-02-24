package org.focusns.web.widget.filter;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.config.WidgetConfig;

public class NotMyProjectFilter extends MyProjectFilter {
    
    public boolean doFilter(WidgetRequest request, WidgetResponse response, WidgetConfig widgetConfig) throws Exception {
        return !super.doFilter(request, response, widgetConfig);
    }
}
