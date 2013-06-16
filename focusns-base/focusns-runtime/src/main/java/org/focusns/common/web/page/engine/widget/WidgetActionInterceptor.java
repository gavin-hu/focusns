package org.focusns.common.web.page.engine.widget;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WidgetActionInterceptor {

    void beforeAction(HttpServletRequest request, HttpServletResponse response);

    void afterAction(HttpServletRequest request, HttpServletResponse response);

}
