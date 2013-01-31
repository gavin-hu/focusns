
package org.focusns.web.widget;

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


import java.util.Map;


public interface WidgetRequest {

    String getWidgetPreference(String name);

	String getRequestParameter(String name);
	
	<T> T getRequestAttribute(String name);
	
	<T> T getSessionAttribute(String name);

	<T> T getApplicationAttribute(String name); 

    Map<String, Object> getWidgetPreferences();

	Map<String, Object> getRequestParameters();
	
	Map<String, Object> getRequestAttributes();
	
	Map<String, Object> getSessionAttributes();
	
	Map<String, Object> getApplicationAttributes();
	
}
