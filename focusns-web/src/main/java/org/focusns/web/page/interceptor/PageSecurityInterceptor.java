package org.focusns.web.page.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;
import org.focusns.common.web.page.config.PageConfig;
import org.focusns.common.web.page.engine.PageRenderInterceptor;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageSecurityInterceptor implements PageRenderInterceptor {

    @Override
    public boolean beforeRender(HttpServletRequest request, HttpServletResponse response) {
        //
        PageConfig pageConfig = (PageConfig) request.getAttribute("pageConfig");
        String authority = pageConfig.getAuthority();
        if(StringUtils.hasText(authority)) {
            authority = StringUtils.replace(authority, "-", ":");
            WebSubject webSubject = (WebSubject) SecurityUtils.getSubject();
            return webSubject.isPermitted(authority);
        }
        //
        return true;
    }

    @Override
    public void afterRender(HttpServletRequest request, HttpServletResponse response) {
    }
}
