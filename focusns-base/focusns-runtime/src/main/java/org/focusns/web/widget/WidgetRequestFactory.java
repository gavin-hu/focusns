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
package org.focusns.web.widget;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.focusns.web.widget.http.HttpWidgetRequest;

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

	public WidgetRequest createWidgetRequest() {
		//
		return new HttpWidgetRequest(requestParameters, requestAttributes, sessionAttributes, applicationAttributes);
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
