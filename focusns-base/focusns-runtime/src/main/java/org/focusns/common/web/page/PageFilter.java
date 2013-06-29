package org.focusns.common.web.page;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.focusns.common.web.WebUtils;
import org.focusns.common.web.page.config.PageConfig;
import org.focusns.common.web.page.config.PageFactory;
import org.focusns.common.web.page.engine.PageEngine;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

class PageFilter extends OncePerRequestFilter {
    private static final String DEFAULT_LAYOUT_LOCATION = "/WEB-INF/themes/default/layout.jsp";
    //
    private PageFactory pageFactory;
    private PageEngine pageEngine;
    private UrlPathHelper urlPathHelper = new UrlPathHelper();
    //
    private String defaultLayout = DEFAULT_LAYOUT_LOCATION;

    public void setDefaultLayout(String defaultLayout) {
        this.defaultLayout = defaultLayout;
    }

    public void setPageFactory(PageFactory pageFactory) {
        this.pageFactory = pageFactory;
    }

    public void setPageEngine(PageEngine pageEngine) {
        this.pageEngine = pageEngine;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //
        String lookupPath = this.urlPathHelper.getLookupPathForRequest(request);
        if (lookupPath.equals("/") || lookupPath.equals("")) {
            lookupPath = "/index";
        }
        //
        PageConfig pageConfig = pageFactory.find(lookupPath, WebUtils.getParameterMap(request));
        if (pageConfig != null) {
            request.setAttribute("pageConfig", pageConfig);
            request.getSession().setAttribute("pageConfig", pageConfig);
            pageEngine.doRender(request, response);
            //
            String layout = !StringUtils.hasText(pageConfig.getLayout()) ? defaultLayout : pageConfig.getLayout();
            request.getRequestDispatcher(layout).forward(request, response);
            //
            return;
        }
        //
        String widgetId = request.getParameter("widgetId");
        if (widgetId != null) {
            request.setAttribute("widgetId", widgetId);
            pageEngine.doAction(request, response);
            //
            return;
        }
        //
        filterChain.doFilter(request, response);
    }

}
