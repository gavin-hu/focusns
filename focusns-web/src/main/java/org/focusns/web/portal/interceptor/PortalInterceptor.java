package org.focusns.web.portal.interceptor;

/*
 * #%L
 * FocusSNS Web
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortalInterceptor extends HandlerInterceptorAdapter {

    private Base64 base64 = new Base64();
    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        String requestPath = urlPathHelper.getOriginatingServletPath(request);
        String requestQueryString = urlPathHelper.getOriginatingQueryString(request);
        if (StringUtils.hasText(requestQueryString)) {
            requestPath += "?" + requestQueryString;
        }
        requestPath = base64.encodeToString(requestPath.getBytes());
        request.setAttribute("currentPath", requestPath);
        //
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //
        if (modelAndView == null) {
            return;
        }
        //
        String viewName = modelAndView.getViewName();
        if (viewName != null && viewName.contains(":")) {
            return;
        }
        //
        modelAndView.setViewName("default/layout");
    }

}
