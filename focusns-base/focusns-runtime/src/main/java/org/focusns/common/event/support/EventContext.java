package org.focusns.common.event.support;

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

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.focusns.common.event.annotation.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.util.ClassUtils;

public class EventContext extends ApplicationContextEvent {

    private Event event;
    //
    private Method eventHandler;
    private Object eventSubscriber;
    //
    private Method method;
    private Object returnValue;
    private Throwable throwable;
    private Map<String, Object> arguments = new LinkedHashMap<String, Object>();

    public EventContext(ApplicationContext appContext, Method method, Map<String, Object> arguments) {
        super(appContext);
        //
        this.method = method;
        this.arguments = arguments;
    }

    public EventContext(ApplicationContext appContext, Method method, Map<String, Object> arguments, Object returnValue) {
        super(appContext);
        //
        this.method = method;
        this.arguments = arguments;
        this.returnValue = returnValue;
    }

    public EventContext(ApplicationContext appContext, Method method, Map<String, Object> arguments,
            Object returnValue, Throwable throwable) {
        super(appContext);
        //
        this.method = method;
        this.returnValue = returnValue;
        this.throwable = throwable;
        this.arguments = arguments;
    }

    public Method getMethod() {
        return method;
    }

    public Object getArgument(String argName) {
        return arguments.get(argName);
    }

    public Object[] getArguments() {
        return arguments.values().toArray();
    }

    @SuppressWarnings("unchecked")
    public <T> T getArgument(String argName, Class<?> requiredType) {
        Object paramValue = arguments.get(argName);
        //
        if (paramValue == null) {
            return null;
        }
        if (ClassUtils.isAssignableValue(requiredType, paramValue)) {
            return (T) paramValue;
        }
        //
        return (T) requiredType.cast(paramValue);
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public Object getThrowable() {
        return throwable;
    }

    Object getEventSubscriber() {
        return eventSubscriber;
    }

    void setEventSubscriber(Object eventSubscriber) {
        this.eventSubscriber = eventSubscriber;
    }

    Method getEventHandler() {
        return eventHandler;
    }

    void setEventHandler(Method eventHandler) {
        this.eventHandler = eventHandler;
    }
}
