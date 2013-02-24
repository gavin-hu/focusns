package org.focusns.web.page.render;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageRender {
	
	boolean matches(String requestPath);

	void doRender(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
