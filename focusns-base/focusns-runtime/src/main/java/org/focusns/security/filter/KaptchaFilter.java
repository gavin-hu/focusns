package org.focusns.security.filter;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class KaptchaFilter extends AccessControlFilter {

    private String kaptchaPath = "/kaptcha";

    private KaptchaExtend kaptchaExtend = new KaptchaExtend();

    public void setKaptchaPath(String kaptchaPath) {
        this.kaptchaPath = kaptchaPath;
    }

    public void setKaptchaExtend(KaptchaExtend kaptchaExtend) {
        this.kaptchaExtend = kaptchaExtend;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        //
        if(isKaptchaRequest(request, response) && GET_METHOD.equals(httpRequest.getMethod())) {
            kaptchaExtend.captcha(httpRequest, httpResponse);
            return false;
        }
        if(isLoginRequest(request, response) && POST_METHOD.equals(httpRequest.getMethod())) {
            String authcode = httpRequest.getParameter("authcode");
            String _authcode = kaptchaExtend.getGeneratedKey(httpRequest);
            //
            if(authcode.equalsIgnoreCase(_authcode)==false) {
                httpRequest.getSession().setAttribute("AuthCodeException", "验证码不匹配！");
                saveRequestAndRedirectToLogin(request, response);
                return false;
            }
        }
        if(isLoginRequest(request, response) && GET_METHOD.equals(httpRequest.getMethod())) {
            Object authCodeException = httpRequest.getSession().getAttribute("AuthCodeException");
            if(authCodeException!=null) {
                httpRequest.getSession().removeAttribute("AuthCodeException");
                httpRequest.setAttribute("AuthCodeException", authCodeException);
            }
        }
        return true;
    }

    protected boolean isKaptchaRequest(ServletRequest request, ServletResponse response) {
        return pathsMatch(kaptchaPath, request);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }
}
