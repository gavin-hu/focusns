package org.focusns.security.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RedirectFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //
        WebSubject webSubject = (WebSubject)getSubject(request, response);
        //
        Object loginFailure = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        Object authenticationException = webSubject.getSession().getAttribute(AuthenticationException.class.getName());
        if(loginFailure==null && authenticationException!=null) {
            webSubject.getSession().removeAttribute(AuthenticationException.class.getName());
            request.setAttribute(AuthenticationException.class.getSimpleName(), authenticationException);
        }
        //
        return loginFailure==null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }
}
