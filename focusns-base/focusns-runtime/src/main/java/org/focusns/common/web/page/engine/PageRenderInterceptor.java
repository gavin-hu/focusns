package org.focusns.common.web.page.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageRenderInterceptor {

    void beforeRender(HttpServletRequest request, HttpServletResponse response);

    void afterRender(HttpServletRequest request, HttpServletResponse response);

}
