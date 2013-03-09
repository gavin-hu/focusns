package org.focusns.web.interceptor;

/*
 * #%L
 * FocusSNS Web
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */


import org.focusns.model.console.User;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
