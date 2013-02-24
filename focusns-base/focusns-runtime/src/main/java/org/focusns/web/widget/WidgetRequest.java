package org.focusns.web.widget;

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
