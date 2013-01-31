
package org.focusns.web.widget.engine;


public interface WidgetFactory {

	Object getWidget(String className) throws Exception;
	
}
