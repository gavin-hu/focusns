package org.focusns.web.interceptor;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.util.UrlPathHelper;

import freemarker.template.Template;

public class RedirectInterceptor extends HandlerInterceptorAdapter {
    
    private UrlPathHelper urlPathHelepr = new UrlPathHelper();

    private FreeMarkerConfig freeMarkerConfig;
    
    private Map<String, String> redirectMappings = new HashMap<String, String>();
    
    private Map<String, Template> templateMappings = new HashMap<String, Template>();

    @Autowired
    public void setFreeMarkerConfig(FreeMarkerConfig freeMarkerConfig) {
        this.freeMarkerConfig = freeMarkerConfig;
    }
    
    public void setRedirectMappings(Map<String, String> redirectMappings) {
        this.redirectMappings = redirectMappings;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
        //
        if(modelAndView==null) {
            return ;
        }
        //X
        if(modelAndView.getViewName()!=null) {
            return ;
        }
        //
        String requestPath = urlPathHelepr.getPathWithinApplication(request);
        if(redirectMappings.containsKey(requestPath)) {
            String mappedPath = getMappedPath(request, modelAndView);
            modelAndView.setViewName("redirect:".concat(mappedPath));
        }
        //
        String redirect = request.getParameter("redirect");
        if(StringUtils.hasText(redirect)) {
           modelAndView.setViewName("redirect:".concat(redirect));
        }
    }

    private String getMappedPath(HttpServletRequest request, ModelAndView modelAndView) throws Exception {
        String requestPath = urlPathHelepr.getPathWithinApplication(request);
        Template template = templateMappings.get(requestPath);
        if(template==null) {
            template = new Template(requestPath, 
                                    new StringReader(redirectMappings.get(requestPath)), 
                                    freeMarkerConfig.getConfiguration());
            templateMappings.put(requestPath, template);
        }
        //
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, modelAndView.getModel());
    }
    
}
