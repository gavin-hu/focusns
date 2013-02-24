package org.focusns.web.widget.engine.impl;

import java.lang.reflect.Method;
import java.util.Locale;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.engine.WidgetInvoker;
import org.focusns.web.widget.engine.WidgetParameterResolver;
import org.focusns.web.widget.engine.WidgetView;
import org.focusns.web.widget.engine.WidgetViewResolver;
import org.springframework.util.ReflectionUtils;

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
