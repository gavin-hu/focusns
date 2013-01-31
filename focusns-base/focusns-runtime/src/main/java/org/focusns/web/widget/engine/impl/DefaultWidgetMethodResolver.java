
package org.focusns.web.widget.engine.impl;

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


import org.focusns.web.widget.engine.WidgetMethod;
import org.focusns.web.widget.engine.WidgetMethodResolver;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
