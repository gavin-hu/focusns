
package org.focusns.common.event.support;

import org.focusns.common.event.annotation.Subscriber;
import org.focusns.common.event.annotation.Subscriber.OnEvent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class EventMulticaster extends SimpleApplicationEventMulticaster 
    implements ApplicationContextAware, InitializingBean {
    
    private ApplicationContext applicationContext;
    
    private ApplicationListener eventListener;
    
    private Map<Method, String> eventSubscribers = new HashMap<Method, String>();
    
    private Map<String, List<Method>> eventHandlers = new HashMap<String, List<Method>>();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       this.applicationContext = applicationContext;
    }

    public Map<String, List<Method>> getEventHandlers() {
        return eventHandlers;
    }

    public Map<Method, String> getEventSubscribers() {
        return eventSubscribers;
    }

    public void afterPropertiesSet() throws Exception {
        //
        scanSubscribers(applicationContext);
        //
        initEventListener(applicationContext);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        if(ClassUtils.isAssignableValue(EventContext.class, event)) {
            //
            Executor executor = getTaskExecutor();
            EventContext eventCtx = (EventContext) event;
            List<Method> handlers = getEventHandlers().get(eventCtx.getTrigger().event());
            if(handlers==null) {
                return ;
            }
            //
            for(Method handler : handlers) {
                OnEvent onEvent = AnnotationUtils.getAnnotation(handler, OnEvent.class);
                if(onEvent.async() && executor!=null) {
                    executor.execute(new Runnable() {
                        @SuppressWarnings("unchecked")
                        public void run() {
                            eventListener.onApplicationEvent(event);
                        }
                    });
                } else {
                    eventListener.onApplicationEvent(event);
                }
            }
        } 
        //
        super.multicastEvent(event);
    }
    
    private void scanSubscribers(ApplicationContext appContext) {
        Map<String, Object> subscriberMap = appContext.getBeansWithAnnotation(Subscriber.class);
        for(String subscriberName : subscriberMap.keySet()) {
            Class<?> subscriberType = appContext.getType(subscriberName);
            Method[] methods = subscriberType.getDeclaredMethods();
            for(Method method : methods) {
                Subscriber.OnEvent onEvent = AnnotationUtils.getAnnotation(method, Subscriber.OnEvent.class);
                if(onEvent!=null) {
                    this.eventSubscribers.put(method, subscriberName);
                    List<Method> handlers = this.eventHandlers.get(onEvent.value());
                    if(handlers==null) {
                        handlers = new ArrayList<Method>();
                    }
                    handlers.add(method);
                    this.eventHandlers.put(onEvent.value(), handlers);
                }
            }
        }
    }

    private void initEventListener(ApplicationContext applicationContext) {
        this.eventListener = new EventListener(eventSubscribers, eventHandlers);
    }

}
