
package org.focusns.common.event.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

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
