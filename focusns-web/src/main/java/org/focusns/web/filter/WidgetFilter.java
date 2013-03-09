package org.focusns.web.filter;

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


import org.focusns.model.core.Project;
import org.focusns.service.core.ProjectService;
import org.focusns.web.portal.config.PageConfig;
import org.focusns.web.portal.config.xml.XmlPageConfigFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.ServletContextResourceLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WidgetFilter extends GenericFilterBean {

    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    //
    private ProjectService projectService;
    //
    private XmlPageConfigFactory pageConfigFactory;

    @Override
    protected void initFilterBean() throws ServletException {
        //
        this.projectService = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext())
                .getBean("projectServiceImpl", ProjectService.class);
        //
        ResourceLoader resourceLoader = new ServletContextResourceLoader(getServletContext());
        this.pageConfigFactory = new XmlPageConfigFactory();
        this.pageConfigFactory.setResourceLoader(resourceLoader);
    }

    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            //
        try {
            String category = null;
            //
            String projectId = request.getParameter("projectId");
            if(StringUtils.hasText(projectId)) {
                Project project = projectService.getProject(projectId);
                if(project!=null) {
                    category = project.getCategory().getCode();
                }
            }
            //
            String requestPath = urlPathHelper.getPathWithinApplication(request);
            PageConfig pageConfig = pageConfigFactory.findPage(category, requestPath);
            //
            if(pageConfig!=null) {
                request.setAttribute("pageConfig", pageConfig);
                request.getRequestDispatcher("/portal").forward(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilterInternal((HttpServletRequest)request, (HttpServletResponse)response, chain);
    }
}
