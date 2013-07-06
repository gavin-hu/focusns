package org.focusns.web.page.interceptor;

import org.focusns.common.web.page.engine.widget.WidgetActionInterceptor;
import org.focusns.model.core.ProjectUser;
import org.focusns.web.Keys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WidgetActionParameterResolver implements WidgetActionInterceptor {
    @Override
    public void beforeAction(HttpServletRequest request, HttpServletResponse response) {
        //
        HttpSession httpSession = request.getSession();
        ProjectUser projectUser = (ProjectUser) httpSession.getAttribute(Keys.SESSION_PROJECT_USER);
        if(projectUser!=null) {
            request.setAttribute(Keys.REQUEST_PROJECT_USER, projectUser);
        }
    }

    @Override
    public void afterAction(HttpServletRequest request, HttpServletResponse response) {
    }
}
