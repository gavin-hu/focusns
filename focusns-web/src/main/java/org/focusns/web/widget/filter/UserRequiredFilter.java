

package org.focusns.web.widget.filter;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.engine.WidgetFilter;

public class UserRequiredFilter implements WidgetFilter {

    @Override
    public boolean doFilter(WidgetRequest request, WidgetResponse response, WidgetConfig widgetConfig) throws Exception {
        //
        return request.getRequestAttributes().containsKey("user");
    }
}
