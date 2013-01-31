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


import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.engine.WidgetInvoker;
import org.focusns.web.widget.engine.WidgetParameterResolver;
import org.focusns.web.widget.engine.WidgetView;
import org.focusns.web.widget.engine.WidgetViewResolver;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Locale;

public class DefaultWidgetInvoker extends WidgetInvoker {
	
	public DefaultWidgetInvoker(Object target, Method method, 
			WidgetRequest request, WidgetResponse response, 
			WidgetViewResolver viewResolver, WidgetParameterResolver parameterResolver) {
		super(target, method, request, response, viewResolver, parameterResolver);
	}
	
	public void invoke() throws Exception {
		//
		Object[] parameters = getParameterResolver().resolve(getMethod(), getRequest(), getResponse());
		//
		Object viewName = ReflectionUtils.invokeMethod(getMethod(), getTarget(), parameters);
		// 
        if(viewName instanceof String && !((String)viewName).startsWith("/")) {
            viewName = getPackagePath(getTarget().getClass()).concat(viewName.toString());
        }
        //
		WidgetView widgetView = getViewResolver().resolve(viewName, Locale.getDefault());
		widgetView.render(getRequest(), getResponse());
	}
    
    private String getPackagePath(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        return "/" + packageName.replaceAll("\\.", "/") + "/";
    }
    
}
