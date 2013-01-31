
package org.focusns.web.widget.engine;

public interface WidgetMethodResolver {
    
    WidgetMethod resolve(String className, String methodName) throws Exception;
    
}
