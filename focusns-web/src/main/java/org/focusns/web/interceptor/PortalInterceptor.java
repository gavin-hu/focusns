package org.focusns.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

public class PortalInterceptor extends HandlerInterceptorAdapter {

    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        String requestUri = urlPathHelper.getOriginatingRequestUri(request);
        if(StringUtils.hasText(requestUri) && requestUri.contains("favicon")) {
            return false;
        }
        //
        return true;
    }
    
}
