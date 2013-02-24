package org.focusns.web.widget;

import javax.servlet.http.HttpServletResponse;

import org.focusns.web.widget.http.HttpWidgetResponse;

public class WidgetResponseFactory {
	
	public WidgetResponseFactory(HttpServletResponse response) {
	}

	public WidgetResponse createWidgetResponse() {
		return new HttpWidgetResponse();
	}
	
}
