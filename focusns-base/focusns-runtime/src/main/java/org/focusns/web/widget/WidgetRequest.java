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

import java.util.Map;


public interface WidgetRequest {

	String getRequestParameter(String name);
	
	<T> T getRequestAttribute(String name);
	
	void setRequestAttribute(String name, Object value);
	
	<T> T getSessionAttribute(String name);

	<T> T getApplicationAttribute(String name); 
	
	Map<String, Object> getRequestParameters();
	
	Map<String, Object> getRequestAttributes();
	
	Map<String, Object> getSessionAttributes();
	
	Map<String, Object> getApplicationAttributes();
	
}
