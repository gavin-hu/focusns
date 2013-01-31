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


import org.focusns.common.event.annotation.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventContext extends ApplicationContextEvent {
    
    private Trigger trigger;
    
    private Method method;
    
    private Object returnValue;
    
    private Map<String, Object> parameters = new HashMap<String, Object>();
    
    public EventContext(ApplicationContext appContext, Method method, Map<String, Object> parameters) {
        super(appContext);
        //
        this.method = method;
        this.parameters = parameters;
    }
    
    public EventContext(ApplicationContext appContext, Method method, Map<String, Object> parameters, Object returnValue) {
        super(appContext);
        //
        this.method = method;
        this.parameters = parameters;
        this.returnValue = returnValue;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
    
    public Method getMethod() {
        return method;
    }
    
    public Object getParameter(String paramName) {
        return parameters.get(paramName);
    }
    
    public <T> T getParameter(String paramName, Class<?> requiredType) {
        Object paramValue = parameters.get(paramName);
        //
        if(paramValue==null) {
            return null;
        }
        if(ClassUtils.isAssignableValue(requiredType, paramValue)) {
            return (T) paramValue;
        }
        //
        return (T) requiredType.cast(paramValue);
    }

    public Object getReturnValue() {
        return returnValue;
    }

}
