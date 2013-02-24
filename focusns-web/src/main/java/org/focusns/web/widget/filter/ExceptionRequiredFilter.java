package org.focusns.web.widget.filter;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.engine.WidgetFilter;

public class ExceptionRequiredFilter implements WidgetFilter {

	public boolean doFilter(WidgetRequest request, WidgetResponse response,
			WidgetConfig widgetConfig) throws Exception {
		return request.getRequestAttributes().containsKey("exception");
	}

}
