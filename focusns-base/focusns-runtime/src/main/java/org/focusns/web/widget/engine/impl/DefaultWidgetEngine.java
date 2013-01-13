/*
 * Copyright (C) 2011-2013 FocusSNS.
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
package org.focusns.web.widget.engine.impl;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.engine.*;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;

public class DefaultWidgetEngine implements WidgetEngine {
	
	private WidgetFactory widgetFactory;

	private WidgetViewResolver widgetViewResolver;
    
    private WidgetMethodResolver widgetMethodResolver = new DefaultWidgetMethodResolver();
	
	private WidgetParameterResolver widgetParameterResolver = new DefaultWidgetParameterResolver();
	
	private ExecutorService executorService = Executors.newCachedThreadPool();
	//
	private ThreadLocal<List<Future<WidgetInvocation>>> futuresLocal = 
			new ThreadLocal<List<Future<WidgetInvocation>>>(){
                @Override
                protected java.util.List<java.util.concurrent.Future<WidgetInvocation>> initialValue() {
                    return new ArrayList<Future<WidgetInvocation>>();
                };
            };
	
	public DefaultWidgetEngine() {
	}
	
	public void setWidgetFactory(WidgetFactory widgetFactory) {
		this.widgetFactory = widgetFactory;
	}
	
	public void setWidgetViewResolver(WidgetViewResolver widgetViewResolver) {
		this.widgetViewResolver = widgetViewResolver;
	}
	
	public void setWidgetParameterResolver(
			WidgetParameterResolver widgetParameterResolver) {
		this.widgetParameterResolver = widgetParameterResolver;
	}
	
	public void submit(final WidgetInvocation invocation) {
		Future<WidgetInvocation> future = executorService.submit(new WidgetInvoker(invocation));
		//
		futuresLocal.get().add(future);
	}
	
	public void submit(List<WidgetInvocation> invocations) {
		for(WidgetInvocation invocation : invocations) {
			submit(invocation);
		}
	}

	public void waitForUntilException() throws Exception {
		List<WidgetInvocation> invocationList = new ArrayList<WidgetInvocation>();
		for(Future<WidgetInvocation> future : futuresLocal.get()) {
			WidgetInvocation invocation = future.get(10, TimeUnit.SECONDS);
			//
			invocationList.add(invocation);
		}
	}
	
	private class WidgetInvoker implements Callable<WidgetInvocation> {
		
		private WidgetInvocation widgetInvocation;
		
		public WidgetInvoker(WidgetInvocation widgetInvocation) {
			this.widgetInvocation = widgetInvocation;
		}

		public WidgetInvocation call() throws Exception {
			//
			String className = widgetInvocation.getWidgetConfig().getClassName();
			String methodName = widgetInvocation.getWidgetConfig().getMethodName();
            WidgetMethod widgetMethod = widgetMethodResolver.resolve(className, methodName);
            //
			WidgetRequest widgetRequest = widgetInvocation.getWidgetRequest();
			WidgetResponse widgetResponse = widgetInvocation.getWidgetResponse();
			//
			Object widget = widgetFactory.getWidget(className); 
			Method method = widgetMethod.getMethod();
			Object[] args = widgetParameterResolver.resolve(method, widgetRequest, widgetResponse);
			//
			Object viewName = ReflectionUtils.invokeMethod(method, widget, args);
			WidgetView widgetView = widgetViewResolver.resolve(viewName, Locale.getDefault());
			//
			widgetView.render(widgetRequest, widgetResponse);
			//
			return widgetInvocation;
		}
		
	}

}
