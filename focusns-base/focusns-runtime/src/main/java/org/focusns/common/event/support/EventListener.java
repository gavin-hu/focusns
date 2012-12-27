/*
 * Copyright (C) 2012 FocuSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.common.event.support;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.util.ReflectionUtils;

public class EventListener implements ApplicationListener<EventContext> {

    private static final Log log = LogFactory.getLog(EventListener.class);

    private Map<Method, String> eventSubscribers;
    
    private Map<String, List<Method>> eventHandlers;

    public EventListener(Map<Method, String> eventSubscribers, Map<String, List<Method>> eventHandlers) {
        this.eventSubscribers = eventSubscribers;
        this.eventHandlers = eventHandlers;
    }
    
    public void onApplicationEvent(EventContext event) {
        log.info(String.format("Event &s triggered!", event.getTrigger().event()));
        List<Method> handlers = eventHandlers.get(event.getTrigger().event());
        //
        if(handlers!=null) {
            for(Method handler : handlers) {
                String handlerName = eventSubscribers.get(handler);
                Object handlerBean = event.getApplicationContext().getBean(handlerName);
                ReflectionUtils.invokeMethod(handler, handlerBean, event);
            }
        }
    }
    
}
