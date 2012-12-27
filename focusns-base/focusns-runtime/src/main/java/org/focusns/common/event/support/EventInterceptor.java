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
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.focusns.common.event.annotation.Trigger;
import org.focusns.common.event.annotation.Trigger.Point;
import org.focusns.common.event.annotation.Triggers;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

@Aspect
public class EventInterceptor implements ApplicationContextAware {

    private static final Log log = LogFactory.getLog(EventInterceptor.class);
    
    private ApplicationContext applicationContext;
    
    private Map<String, Method> methodCache = new HashMap<String, Method>();
    
    private ParameterNameDiscoverer paramNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    @Around("@annotation(org.focusns.common.event.annotation.Trigger) || "
            + "@annotation(org.focusns.common.event.annotation.Triggers)")
    public Object weave(ProceedingJoinPoint pjp) throws Throwable {
        //MethodInvocation invocation = (MethodInvocation) pjp;
        //
        Object[] args = pjp.getArgs();
        Method method = getMethod(pjp);
        EventContext context = buildTaskContext(method, args);
        Map<Point, List<Trigger>> triggerMap = getTriggerMap(method);
        //
        Object result = null;
        try {
            triggerEvent(context, Point.BEFORE, triggerMap);
            result = pjp.proceed(args);
            triggerEvent(context, Point.AFTER, triggerMap);
        } catch (Throwable t) {
            log.error(t.getMessage(), t);
            //
            triggerEvent(context, Point.AFTER_THROWING, triggerMap);
            throw t;
        } finally {
            return result;
        }
    }
    
    public void triggerEvent(EventContext context, Point point, 
            Map<Point, List<Trigger>> triggerMap) throws Exception {
        //
        List<Trigger> triggers = triggerMap.get(point);
        if(triggers!=null) {
            for(Trigger trigger : triggerMap.get(point)) {
                //
                if(log.isDebugEnabled()) {
                    log.debug(String.format("Trigger %s at point %s", 
                            trigger.event(), trigger.point()));
                }
                //
                context.setTrigger(trigger);
                //
                applicationContext.publishEvent(context);
            }
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
			for (Method method : classType.getDeclaredMethods()) {
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
    
    private boolean isSameMethod(Method target, String methodName, Object[] paramValues) {
        Class<?>[] paramTypes = target.getParameterTypes();
        for(int i=0; i<paramTypes.length; i++) {
            if(methodName.equals(target.getName()) &&
                    ClassUtils.isAssignableValue(paramTypes[i], paramValues[i])) {
                return true;
            }
        }
        //
        return false;
    }
    
    protected Map<Point, List<Trigger>> getTriggerMap(Method method) {
        Map<Point, List<Trigger>> triggerMap = new EnumMap<Point, List<Trigger>>(Point.class);
        //
        Triggers triggers = AnnotationUtils.getAnnotation(method, Triggers.class);
        if(triggers!=null) {
            for(Trigger trigger : triggers.value()) {
                List<Trigger> triggerList = getTriggerList(triggerMap, trigger.point());
                triggerList.add(trigger);
                triggerMap.put(trigger.point(), triggerList);
            }
        }
        //
        Trigger trigger = AnnotationUtils.getAnnotation(method, Trigger.class);
        if(trigger!=null) {
            List<Trigger> triggerList = getTriggerList(triggerMap, trigger.point());
            triggerList.add(trigger);
            triggerMap.put(trigger.point(), triggerList);
        }
        //
        return triggerMap;
    }
    
    private List<Trigger> getTriggerList(Map<Point, List<Trigger>> triggerMap, Point point) {
        List<Trigger> triggerList = triggerMap.get(point);
        if(triggerList==null) {
            triggerList = new ArrayList<Trigger>();
        }
        //
        return triggerList;
    }

    private EventContext buildTaskContext(Method method, Object[] args) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        String[] paramNames = paramNameDiscoverer.getParameterNames(method);
        if(paramNames!=null) {
            for(int i=0; i<paramNames.length; i++) {
                parameters.put(paramNames[i], args[i]);
            }
        }
        return new EventContext(applicationContext, method, parameters);
    }
    
}
