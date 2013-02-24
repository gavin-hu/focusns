package org.focusns.web.widget.engine.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.annotation.AfterFilter;
import org.focusns.web.widget.annotation.BeforeFilter;
import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.engine.WidgetEngine;
import org.focusns.web.widget.engine.WidgetFactory;
import org.focusns.web.widget.engine.WidgetFilter;
import org.focusns.web.widget.engine.WidgetFilterResolver;
import org.focusns.web.widget.engine.WidgetInvocation;
import org.focusns.web.widget.engine.WidgetMethod;
import org.focusns.web.widget.engine.WidgetMethodResolver;
import org.focusns.web.widget.engine.WidgetParameterResolver;
import org.focusns.web.widget.engine.WidgetView;
import org.focusns.web.widget.engine.WidgetViewResolver;
import org.springframework.util.ReflectionUtils;

public class DefaultWidgetEngine implements WidgetEngine {
	
	private WidgetFactory widgetFactory;

	private WidgetViewResolver widgetViewResolver;
    
    private WidgetMethodResolver widgetMethodResolver = new DefaultWidgetMethodResolver();
	
	private WidgetParameterResolver widgetParameterResolver = new DefaultWidgetParameterResolver();

    private WidgetFilterResolver widgetFilterResolver = new DefaultWidgetFilterResolver();
	
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
	
	public void setWidgetParameterResolver(WidgetParameterResolver widgetParameterResolver) {
		this.widgetParameterResolver = widgetParameterResolver;
	}

    public void setWidgetFilterResolver(WidgetFilterResolver widgetFilterResolver) {
        this.widgetFilterResolver = widgetFilterResolver;
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
            WidgetConfig widgetConfig = widgetInvocation.getWidgetConfig();
			WidgetRequest widgetRequest = widgetInvocation.getWidgetRequest();
			WidgetResponse widgetResponse = widgetInvocation.getWidgetResponse();
            //
			Object widget = widgetFactory.getWidget(className); 
			Method method = widgetMethod.getMethod();
            //
            List<WidgetFilter> widgetBeforeFilters = widgetFilterResolver.resolve(method, widgetConfig, BeforeFilter.class);
            for(WidgetFilter widgetFitler : widgetBeforeFilters) {
                if(!widgetFitler.doFilter(widgetRequest, widgetResponse, widgetConfig)) {
                    return widgetInvocation;
                }
            }
            //
			Object[] args = widgetParameterResolver.resolve(method, widgetRequest, widgetResponse);
			//
			Object viewName = ReflectionUtils.invokeMethod(method, widget, args);
			//
            List<WidgetFilter> widgetAfterFilters = widgetFilterResolver.resolve(method, widgetConfig, AfterFilter.class);
            for(WidgetFilter widgetFitler : widgetAfterFilters) {
                if(!widgetFitler.doFilter(widgetRequest, widgetResponse, widgetConfig)) {
                    return widgetInvocation;
                }
            }
            //
            WidgetView widgetView = widgetViewResolver.resolve(viewName, Locale.getDefault());
			//
			widgetView.render(widgetRequest, widgetResponse);
            // commit widget response
            widgetResponse.commit();
			//
			return widgetInvocation;
		}
		
	}

}
