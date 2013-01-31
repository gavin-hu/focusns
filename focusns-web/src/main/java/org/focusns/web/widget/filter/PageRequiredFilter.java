

package org.focusns.web.widget.filter;

import org.focusns.model.common.Page;
import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.engine.WidgetFilter;

public class PageRequiredFilter implements WidgetFilter {

    @Override
    public boolean doFilter(WidgetRequest request, WidgetResponse response, WidgetConfig widgetConfig) throws Exception {
        //
        Page page = request.getRequestAttribute("page");
        //
        return page!=null && !page.getResults().isEmpty();
    }
}
