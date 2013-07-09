package org.focusns.security.filter;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.subject.WebSubject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //
        super.onAccessDenied(request, response);
        //
        Object loginFailure = request.getAttribute(org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if(loginFailure!=null) {
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
        //
        WebSubject webSubject = (WebSubject)getSubject(request, response);
        Object authenticationException = webSubject.getSession().getAttribute(AuthenticationException.class.getName());
        if(loginFailure==null && authenticationException!=null) {
            webSubject.getSession().removeAttribute(AuthenticationException.class.getName());
            request.setAttribute(AuthenticationException.class.getSimpleName(), authenticationException);
        }
        //
        return true;
    }

}
