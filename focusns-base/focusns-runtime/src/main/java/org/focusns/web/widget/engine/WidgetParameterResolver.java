
package org.focusns.web.widget.engine;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;

import java.lang.reflect.Method;

public interface WidgetParameterResolver {
    
    Object UNRESOLVED = new Object();

	Object[] resolve(Method method, WidgetRequest request, WidgetResponse response);
	
}
