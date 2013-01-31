
package org.focusns.web.widget.engine;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;

public interface WidgetView {

	void render(WidgetRequest request, WidgetResponse widgetResponse) throws Exception;
	
}
