package org.focusns.web.widget.engine.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.focusns.web.widget.engine.WidgetMethod;
import org.focusns.web.widget.engine.WidgetMethodResolver;

public class DefaultWidgetMethodResolver implements WidgetMethodResolver {

    private final static Map<String, WidgetMethod> widgetMethodCache = new HashMap<String, WidgetMethod>();
    
    public WidgetMethod resolve(String className, String methodName) throws Exception {
        //
        String key = className+"@"+methodName;
        if(widgetMethodCache.containsKey(key)) {
				return widgetMethodCache.get(key);
		}
        //
        Method method = findMethod(className, methodName);
        WidgetMethod widgetMethod = new WidgetMethod(method);
        widgetMethodCache.put(key, widgetMethod);
        //
        return widgetMethod;
    }
    
    private Method findMethod(String className, String methodName) throws ClassNotFoundException, NoSuchMethodException {
        //
        Class<?> clazz = Class.forName(className);
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods) {
            if(methodName.equals(method.getName())) {
                return method;
            }
        }
        //
        throw new NoSuchMethodException(String.format("Method %s not found in class %s", methodName, className));
    }
    
}
