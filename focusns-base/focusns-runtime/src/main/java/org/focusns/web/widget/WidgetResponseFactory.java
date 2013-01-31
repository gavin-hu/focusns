
package org.focusns.web.widget;

import org.focusns.web.widget.http.HttpWidgetResponse;

import javax.servlet.http.HttpServletResponse;

public class WidgetResponseFactory {
	
	public WidgetResponseFactory(HttpServletResponse response) {
	}

	public WidgetResponse createWidgetResponse() {
		return new HttpWidgetResponse();
	}
	
}
