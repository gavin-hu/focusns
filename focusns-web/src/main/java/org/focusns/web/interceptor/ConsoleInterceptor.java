package org.focusns.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.focusns.model.console.User;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

public class ConsoleInterceptor extends HandlerInterceptorAdapter {

    public static final String URL_CONSOLE = "/console";

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        String contextPath = urlPathHelper.getOriginatingContextPath(request);
        String requestPath = urlPathHelper.getOriginatingRequestUri(request);
        requestPath = requestPath.substring(contextPath.length());
        if(StringUtils.hasLength(requestPath)
                && requestPath.startsWith(URL_CONSOLE)
                && requestPath.length() > URL_CONSOLE.length()) {
            //
            if(requestPath.equals("/console/login")) {
                return true;
            }
            //
            User user = (User) request.getSession().getAttribute("user");
            if(user==null || !ClassUtils.isAssignableValue(User.class, user)) {
                //  redirect to /console
                response.sendRedirect(contextPath.concat("/console"));
                return false;
            }
        }
        //
        return true;
    }
}
