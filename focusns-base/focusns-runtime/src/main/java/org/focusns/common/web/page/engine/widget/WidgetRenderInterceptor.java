package org.focusns.common.web.page.engine.widget;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WidgetRenderInterceptor {

    boolean beforeRender(HttpServletRequest request, HttpServletResponse response);

    void afterRender(HttpServletRequest request, HttpServletResponse response);

}
