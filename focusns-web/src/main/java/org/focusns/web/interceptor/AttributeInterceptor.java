
package org.focusns.web.interceptor;

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
