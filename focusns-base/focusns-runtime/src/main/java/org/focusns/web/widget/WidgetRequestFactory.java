package org.focusns.web.widget;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.http.HttpWidgetRequest;
import org.springframework.web.servlet.support.RequestContextUtils;

public class WidgetRequestFactory {

	private Map<String, Object> requestParameters = new ConcurrentHashMap<String, Object>();
	private Map<String, Object> requestAttributes = new ConcurrentHashMap<String, Object>();
	private Map<String, Object> sessionAttributes = new ConcurrentHashMap<String, Object>();
	private Map<String, Object> applicationAttributes = new ConcurrentHashMap<String, Object>();
	
	public WidgetRequestFactory(HttpServletRequest request) {
		copyRequestParameters(request, requestParameters);
		copyRequestAttributes(request, requestAttributes);
		copySessionAttributes(request, sessionAttributes);
		copyApplicationAttributes(request, applicationAttributes);
	}

	public WidgetRequest createWidgetRequest(WidgetConfig widgetConfig) {
		//
		return new HttpWidgetRequest(widgetConfig, requestParameters, requestAttributes, sessionAttributes, applicationAttributes);
	}
	
	@SuppressWarnings("unchecked")
	private static void copyRequestParameters(HttpServletRequest request, Map<String, Object> requestParameters) {
		Enumeration<String> e = request.getParameterNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			Object value = request.getParameter(name);
			//
			requestParameters.put(name, value);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void copyRequestAttributes(HttpServletRequest request, Map<String, Object> requestAttributes) {
		Enumeration<String> e = request.getAttributeNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			Object value = request.getAttribute(name);
			//
			requestAttributes.put(name, value);
		}
        // copy flash attributes
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap!=null) {
            requestAttributes.putAll(flashMap);
        }
	}
	
	@SuppressWarnings("unchecked")
	private static void copySessionAttributes(HttpServletRequest request, Map<String, Object> sessionAttributes) {
		HttpSession session = request.getSession();
		Enumeration<String> e = session.getAttributeNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			Object value = session.getAttribute(name);
			//
			sessionAttributes.put(name, value);
		}
        //
        sessionAttributes.put("id", session.getId());
	}
	
	@SuppressWarnings("unchecked")
	private void copyApplicationAttributes(HttpServletRequest request, Map<String, Object> applicationAttributes) {
		ServletContext application = request.getSession().getServletContext();
		Enumeration<String> e = application.getAttributeNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			Object value = application.getAttribute(name);
			//
			applicationAttributes.put(name, value);
		}
        //
        applicationAttributes.put("WEBROOT", application.getRealPath("/"));
	}
	
}
