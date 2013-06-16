package org.focusns.common.web.tagext.control.event.filter;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.focusns.common.web.tagext.control.event.Event;
import org.focusns.common.web.tagext.control.event.EventHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

public class ControlEventDelegatingFilter extends OncePerRequestFilter {

    private static UrlPathHelper urlPathHelper = new UrlPathHelper();
    //
    private WebApplicationContext componentContext;

    public WebApplicationContext getComponentContext() {
        if (componentContext == null) {
            this.componentContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        }
        return componentContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //
        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        String componentEventHandlerName = lookupPath.substring("/event/".length());
        //
        EventHandler componentEventHandler = getComponentContext().getBean(componentEventHandlerName, EventHandler.class);
        Event componentEvent = resolveComponentEvent(request);
        //
        componentEventHandler.handleEvent(componentEvent);
    }

    protected Event resolveComponentEvent(HttpServletRequest request) {
        //
        String eventName = request.getParameter("event");
        Event controlEvent = new Event(eventName);
        //
        for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
            String paramName = e.nextElement();
            if (paramName.startsWith("event_")) {
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
