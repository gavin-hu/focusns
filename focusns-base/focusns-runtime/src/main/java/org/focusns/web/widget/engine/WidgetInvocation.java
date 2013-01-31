
package org.focusns.web.widget.engine;

import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;

public class WidgetInvocation {

	private WidgetConfig widgetConfig;
	private WidgetRequest widgetRequest;
	private WidgetResponse widgetResponse;
	
	public WidgetInvocation(WidgetConfig widgetConfig,
			WidgetRequest widgetRequest, WidgetResponse widgetResponse) {
		this.widgetConfig = widgetConfig;
		this.widgetRequest = widgetRequest;
		this.widgetResponse = widgetResponse;
	}
	
	public WidgetConfig getWidgetConfig() {
		return widgetConfig;
	}
	
	public WidgetRequest getWidgetRequest() {
		return widgetRequest;
	}
	
	public WidgetResponse getWidgetResponse() {
		return widgetResponse;
	}
	
}
