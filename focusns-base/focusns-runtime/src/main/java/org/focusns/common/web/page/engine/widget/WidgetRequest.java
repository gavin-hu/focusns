package org.focusns.common.web.page.engine.widget;

import org.focusns.common.web.page.config.WidgetConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class WidgetRequest extends HttpServletRequestWrapper implements HttpServletRequest {

    public WidgetRequest(HttpServletRequest request, WidgetConfig widgetConfig, String requestType) {
        super(request);
        super.setAttribute("widgetConfig", widgetConfig);
        super.setAttribute("requestType", requestType);
    }

    public WidgetConfig getWidgetConfig() {
        return (WidgetConfig) super.getAttribute("widgetConfig");
    }

    public String getRequestType() {
         return (String) super.getAttribute("requestType");
    }
}
