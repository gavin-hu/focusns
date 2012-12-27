/*
 * Copyright (C) 2012 FocuSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.web.interceptor;

import freemarker.template.Template;
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

public class RedirectInterceptor extends HandlerInterceptorAdapter {
    
    private UrlPathHelper urlPathHelepr = new UrlPathHelper();

    private FreeMarkerConfig freeMarkerConfig;
    
    private Map<String, String> redirectMappings;
    
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
