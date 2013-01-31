
package org.focusns.web.widget.engine;

import java.util.Locale;

public interface WidgetViewResolver {

	WidgetView resolve(Object viewName, Locale locale) throws Exception;
	
}
