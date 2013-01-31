
package org.focusns.web.widget.engine;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;

import java.lang.reflect.Method;

public abstract class WidgetInvoker {
	
	private String code;
	private Object target;
	private Method method;
	private WidgetRequest request;
	private WidgetResponse response;
	private WidgetViewResolver viewResolver;
	private WidgetParameterResolver parameterResolver;
	
	public WidgetInvoker(Object target, Method method, WidgetRequest request, 
			WidgetResponse response, WidgetViewResolver viewResolver, 
			WidgetParameterResolver parameterResolver) {
		this.target = target;
		this.method = method;
		this.request = request;
		this.response = response;
		this.viewResolver = viewResolver;
		this.parameterResolver = parameterResolver;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Object getTarget() {
		return target;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public WidgetRequest getRequest() {
		return request;
	}
	
	public WidgetResponse getResponse() {
		return response;
	}
	
	public WidgetViewResolver getViewResolver() {
		return viewResolver;
	}
	
	public WidgetParameterResolver getParameterResolver() {
		return parameterResolver;
	}
	
	public abstract void invoke() throws Exception;
	
}
