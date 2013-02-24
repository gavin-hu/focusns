package org.focusns.common.event.support;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.focusns.common.event.annotation.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.util.ClassUtils;

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
    
    @SuppressWarnings("unchecked")
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
