package org.focusns.web.widget.engine;

import java.lang.reflect.Method;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;

public interface WidgetParameterResolver {
    
    Object UNRESOLVED = new Object();

	Object[] resolve(Method method, WidgetRequest request, WidgetResponse response);
	
}
