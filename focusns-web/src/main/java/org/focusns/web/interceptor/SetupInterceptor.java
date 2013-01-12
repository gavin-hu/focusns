/*
 * Copyright (C) 2012 FocusSNS.
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

import com.googlecode.flyway.core.util.StringUtils;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.focusns.model.setup.DbConfig;
import org.focusns.model.setup.SiteConfig;
import org.focusns.runtime.RuntimeHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetupInterceptor extends HandlerInterceptorAdapter 
    implements InitializingBean {

    private FreeMarkerConfig freeMarkerConfig;
    
    private boolean applicationInstalled;
    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    
    private ThreadLocal<String> requestPathLocal = new ThreadLocal<String>();

    @Autowired
    public void setFreeMarkerConfig(FreeMarkerConfig freeMarkerConfig) {
        this.freeMarkerConfig = freeMarkerConfig;
    }

    public void afterPropertiesSet() throws Exception {
        this.applicationInstalled = RuntimeHelper.getInstance().isInstalled();
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, 
            HttpServletResponse response, Object handler) throws Exception {
        //
        String requestPath = getCurrentRequestPath(request);
        //
        if(requestPath.startsWith("/setup")) {
            dispatchSetup(request, response);
            return false;
        } else if(!applicationInstalled) {
            response.sendRedirect(request.getContextPath() + "/setup/step1");
            return false;
        } 
        //
        return true;
    }
    
    private String getCurrentRequestPath(HttpServletRequest request) {
        String requestPath = requestPathLocal.get();
        if(requestPath==null) {
            String requestUri = urlPathHelper.getOriginatingRequestUri(request);
            String contextPath = urlPathHelper.getOriginatingContextPath(request);
            requestPath = requestUri.substring(contextPath.length());
            //
            requestPathLocal.set(requestPath);
        }
        //
        return requestPath;
    }
 
    protected void dispatchSetup(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException, TemplateException {
        //
        String step = null;
        SimpleHash model = buildTemplateModel(request);
        String requestPath = getCurrentRequestPath(request);
        if(requestPath.equals("/setup/step1")) {
            step = "step1";
            setupStep1(request, response, model);
        } else if(requestPath.equals("/setup/step2")) {
            step = "step2";
            setupStep2(request, response, model);
        } else if(requestPath.equals("/setup/step3")){
            step = "step3";
            setupStep3(request, response, model);
        }
        //
        if(StringUtils.hasText(step)) {
            Template template = freeMarkerConfig.getConfiguration()
                    .getTemplate(String.format("/WEB-INF/setup/%s.ftl", step));
            template.process(model, response.getWriter());
            //
            response.setContentType("text/html;encoding=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
    
    private SimpleHash buildTemplateModel(HttpServletRequest request) {
		SimpleHash simpleHash = new SimpleHash();
		simpleHash.put(FreemarkerServlet.KEY_REQUEST_PARAMETERS, request.getParameterMap());
        simpleHash.put("contextPath", request.getContextPath());
		//
		return simpleHash;
	}
    
    protected void setupStep1(HttpServletRequest request, HttpServletResponse response,
            SimpleHash model) throws ServletException, IOException {
        if("post".equalsIgnoreCase(request.getMethod())) {
            DbConfig dbConfig = new DbConfig();
            dbConfig.setType(request.getParameter("type"));
            dbConfig.setHost(request.getParameter("host"));
            dbConfig.setPort(request.getParameter("port"));
            dbConfig.setSchema(request.getParameter("schema"));
            dbConfig.setUsername(request.getParameter("username"));
            dbConfig.setPassword(request.getParameter("password"));
            //
            validateDbConfig(dbConfig);
            //
            request.getSession().setAttribute("dbConfig", dbConfig);
            //
            response.sendRedirect(request.getContextPath() + "/setup/step2");
        }
    }
    
    protected void setupStep2(HttpServletRequest request, HttpServletResponse response,
            SimpleHash model) throws ServletException, IOException {
        if("post".equalsIgnoreCase(request.getMethod())) {
            //
            SiteConfig siteConfig = new SiteConfig();
            siteConfig.setTitle(request.getParameter("title"));
            siteConfig.setKeywords(request.getParameter("keywords"));
            siteConfig.setDescription(request.getParameter("description"));
            //
            validateSiteConfig(siteConfig);
            //
            request.getSession().setAttribute("siteConfig", siteConfig);
            //
            response.sendRedirect(request.getContextPath() + "/setup/step3");
        }
    }
     
    protected void setupStep3(HttpServletRequest request, HttpServletResponse response,
            SimpleHash model) throws ServletException, IOException {
        //
        SiteConfig siteConfig = (SiteConfig) request.getSession().getAttribute("siteConfig");
        DbConfig dbConfig = (DbConfig) request.getSession().getAttribute("dbConfig");
        //
        RuntimeHelper.getInstance().install(siteConfig, dbConfig);
    }

    private boolean validateDbConfig(DbConfig dbConfig) {
        return true;
    }
    
    private boolean validateSiteConfig(SiteConfig siteConfig) {
        return true;
    }
}
