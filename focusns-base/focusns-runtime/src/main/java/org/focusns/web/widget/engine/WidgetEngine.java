package org.focusns.web.widget.engine;

import java.util.List;

public interface WidgetEngine {
	//
	void submit(List<WidgetInvocation> widgetInvocations);
	//
	void waitForUntilException() throws Exception;
}
