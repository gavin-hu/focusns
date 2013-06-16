package org.focusns.common.web.tagext.control.event.filter;

import org.focusns.common.web.tagext.control.event.Event;
import org.focusns.common.web.tagext.control.event.EventHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class ControlEventDelegatingFilter extends OncePerRequestFilter {

    private static UrlPathHelper urlPathHelper = new UrlPathHelper();
    //
    private WebApplicationContext componentContext;

    public WebApplicationContext getComponentContext() {
        if(componentContext==null) {
            this.componentContext = WebApplicationContextUtils
                    .getWebApplicationContext(getServletContext());
        }
        return componentContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        //
        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        String componentEventHandlerName = lookupPath.substring("/event/".length());
        //
        EventHandler componentEventHandler = getComponentContext()
                .getBean(componentEventHandlerName, EventHandler.class);
        Event componentEvent = resolveComponentEvent(request);
        //
        componentEventHandler.handleEvent(componentEvent);
    }

    protected Event resolveComponentEvent(HttpServletRequest request) {
        //
        String eventName = request.getParameter("event");
        Event controlEvent = new Event(eventName);
        //
        for(Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
            String paramName = e.nextElement();
            if(paramName.startsWith("event_")) {
                String eventDataKey = paramName.substring("event_".length());
                String eventDataValue = request.getParameter(paramName);
                //
                controlEvent.getEventData().put(eventDataKey, eventDataValue);
            }
        }
        //
        return controlEvent;
    }

}
