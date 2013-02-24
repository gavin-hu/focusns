package org.focusns.web.widget;

import java.io.OutputStream;
import java.io.PrintWriter;

public interface WidgetResponse {

	PrintWriter getWriter();
	
	OutputStream getOutputStream();

    void commit();

    boolean isCommitted();
	
}
