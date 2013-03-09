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


import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AttributeInterceptor extends HandlerInterceptorAdapter {

	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//
		exposeContextPath(request);
        //
        exposeRequestPath(request);
		//
		return super.preHandle(request, response, handler);
	}

    private void exposeRequestPath(HttpServletRequest request) {
        String contextPath = urlPathHelper.getOriginatingContextPath(request);
        String requestUri = urlPathHelper.getOriginatingRequestUri(request);
        request.setAttribute("requestPath", requestUri.substring(contextPath.length()));

    }

    private void exposeContextPath(HttpServletRequest request) {
        String contextPath = urlPathHelper.getOriginatingContextPath(request);
		request.setAttribute("contextPath", contextPath);
	}
	
}
