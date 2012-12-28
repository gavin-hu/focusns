/*
 * Copyright (C) 2012 FocusSNS.
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
package org.focusns.web.widget.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.focusns.web.widget.WidgetRequest;

public class HttpWidgetRequest implements WidgetRequest {
	
	private Map<String, Object> requestParameters = Collections.emptyMap();
	private Map<String, Object> requestAttributes = Collections.emptyMap();
	private Map<String, Object> sessionAttributes = Collections.emptyMap();
	private Map<String, Object> applicationAttributes = Collections.emptyMap();
	
	public HttpWidgetRequest() {
	}
	
	public HttpWidgetRequest(Map<String, ?> requestParameters, Map<String, ?> requestAttributes, 
			Map<String, ?> sessionAttributes, Map<String, ?> applicationAttributes) {
		this.requestParameters = new HashMap<String, Object>(requestParameters);
		this.requestAttributes = new HashMap<String, Object>(requestAttributes);
		this.sessionAttributes = new HashMap<String, Object>(sessionAttributes);
		this.applicationAttributes = new HashMap<String, Object>(applicationAttributes);
	}

	public String getRequestParameter(String name) {
		return (String) this.requestParameters.get(name);
	}

	@SuppressWarnings("unchecked")
	public <T> T getRequestAttribute(String name) {
		return (T) this.requestAttributes.get(name);
	}
	
	public void setRequestAttribute(String name, Object value) {
		this.requestAttributes.put(name, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getSessionAttribute(String name) {
		return (T) this.sessionAttributes.get(name);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getApplicationAttribute(String name) {
		return (T) this.applicationAttributes.get(name);
	}

	public Map<String, Object> getRequestParameters() {
		return requestParameters;
	}

	public Map<String, Object> getRequestAttributes() {
		return requestAttributes;
	}

	public Map<String, Object> getSessionAttributes() {
		return sessionAttributes;
	}
	
	public Map<String, Object> getApplicationAttributes() {
		return applicationAttributes;
	}

}
