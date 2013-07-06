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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.focusns.common.event.annotation.Event;
import org.focusns.common.event.annotation.EventSubscriber;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

@Aspect
public class EventInterceptor implements BeanFactoryPostProcessor, ApplicationContextAware {

    private static final Log log = LogFactory.getLog(EventInterceptor.class);

    private ApplicationContext appContext;

    private Map<String, Method> methodCache = new HashMap<String, Method>();

    private Map<Method, String> eventKeyCache = new HashMap<Method, String>();

    private Map<String, Event> eventMapping = new HashMap<String, Event>();

    private Map<String, Method> eventMethodMapping = new HashMap<String, Method>();

    private Map<String, String> eventSubscriberMapping = new HashMap<String, String>();

    private ParameterNameDiscoverer paramNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //
        Map<String, Object> beansMap = beanFactory.getBeansWithAnnotation(EventSubscriber.class);
        for (Map.Entry<String, Object> entry : beansMap.entrySet()) {
            String beanName = entry.getKey();
            Object beanObject = entry.getValue();
            Class<?> beanClass = beanObject.getClass();
            //
            Method[] declearedMethods = beanClass.getDeclaredMethods();
            for (Method declearedMethod : declearedMethods) {
                //
                Event event = AnnotationUtils.getAnnotation(declearedMethod, Event.class);
                if (event == null) {
                    log.warn(String.format("Event Subscribe method %s must be annotated with @Event(\"xxx\")", declearedMethod));
                } else {
                    String eventKey = generateEventKey(event);
                    eventMapping.put(eventKey, event);
                    eventMethodMapping.put(eventKey, declearedMethod);
                    eventSubscriberMapping.put(eventKey, beanName);
                }
            }
        }
    }

    @Around("@within(org.springframework.stereotype.Service)")
    public Object weave(ProceedingJoinPoint pjp) throws Throwable {
        // MethodInvocation invocation = (MethodInvocation) pjp;
        Object result = null;
        try {
            triggerEvent(Event.Point.BEFORE, pjp, null, null);
            //
            result = pjp.proceed();
            //

            triggerEvent(Event.Point.AFTER, pjp, result, null);
            //
        } catch (Throwable throwable) {
            //
            triggerEvent(Event.Point.AFTER_THROWING, pjp, result, throwable);
            throw throwable;
        }
        //
        return result;
    }

    protected void triggerEvent(Event.Point point, ProceedingJoinPoint pjp, Object returnValue,
            Throwable throwable) throws Exception {
        //
        Method method = getMethod(pjp);
        Map<String, Object> arguments = getArguments(method, pjp.getArgs());
        //
        Event event = getEvent(method, point);
        //
        if (event != null) {
            //
            EventContext eventContext = null;
            if (point == Event.Point.BEFORE) {
                eventContext = new EventContext(appContext, method, arguments);
            } else if (point == Event.Point.AFTER) {
                eventContext = new EventContext(appContext, method, arguments, returnValue);
            } else if (point == Event.Point.AFTER_THROWING) {
                eventContext = new EventContext(appContext, method, arguments, returnValue, throwable);
            }
            //
            String eventKey = generateEventKey(event);
            String subscriberName = eventSubscriberMapping.get(eventKey);
            Object eventSubscriber = appContext.getBean(subscriberName);
            Method eventHandler = eventMethodMapping.get(eventKey);
            eventContext.setEventHandler(eventHandler);
            eventContext.setEventSubscriber(eventSubscriber);
            //
            appContext.publishEvent(eventContext);
        }
    }

    private Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String key = signature.getDeclaringTypeName() + "." + signature.getName();
        //
        Method target = methodCache.get(key);
        if (target == null) {
            Object[] args = joinPoint.getArgs();
            String methodName = signature.getName();
            Class<?> classType = joinPoint.getTarget().getClass();
            for (Method method : classType.getMethods()) {
                if (isSameMethod(method, methodName, args)) {
                    // cache and return
                    methodCache.put(key, method);
                    return method;
                }
            }
        }
        //
        return target;
    }

    private Event getEvent(Method method, Event.Point point) {
        //
        String eventKey = eventKeyCache.get(method);
        if (eventKey == null) {
            StringBuilder eventKeyBuilder = new StringBuilder();
            String methodName = method.getName();
            for (int i = 0; i < methodName.length(); i++) {
                char c = methodName.charAt(i);
                if (Character.isUpperCase(c)) {
                    eventKeyBuilder.append("_");
                }
                eventKeyBuilder.append(Character.toUpperCase(c));
            }
            //
            eventKey = eventKeyBuilder.append("|").append(point.name()).toString();
        }
        //
        return eventMapping.get(eventKey);
    }

    private boolean isSameMethod(Method target, String methodName, Object[] paramValues) {
        Class<?>[] paramTypes = target.getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            if (methodName.equals(target.getName()) && ClassUtils.isAssignableValue(paramTypes[i], paramValues[i])) {
                return true;
            }
        }
        //
        return false;
    }

    private Map<String, Object> getArguments(Method method, Object[] args) {
        LinkedHashMap<String, Object> argumentMap = new LinkedHashMap<String, Object>();
        String[] paramNames = paramNameDiscoverer.getParameterNames(method);
        if (paramNames != null) {
            for (int i = 0; i < paramNames.length; i++) {
                argumentMap.put(paramNames[i], args[i]);
            }
        }
        return argumentMap;
    }

    private String generateEventKey(Event event) {
        return event.on() + "|" + event.point().name();
    }
}
