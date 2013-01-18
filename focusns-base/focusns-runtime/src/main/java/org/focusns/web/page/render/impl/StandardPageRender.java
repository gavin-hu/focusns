/*
 * Copyright (C) 2011-2013 FocusSNS.
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
package org.focusns.web.page.render.impl;

import org.focusns.web.page.config.PageConfig;
import org.focusns.web.page.config.PageConfigFactory;
import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.page.render.PageRender;
import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetRequestFactory;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.WidgetResponseFactory;
import org.focusns.web.widget.engine.WidgetEngine;
import org.focusns.web.widget.engine.WidgetFilter;
import org.focusns.web.widget.engine.WidgetInvocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class StandardPageRender implements PageRender {

	private WidgetEngine widgetEngine;
    
    private List<WidgetFilter> widgetFilters = Collections.emptyList();
	
	private PageConfigFactory pageConfigFactory;
	
	private ThreadLocal<PageConfig> pageConfigLocal = new ThreadLocal<PageConfig>();
	
	public StandardPageRender() {
	}
	
	public void setWidgetEngine(WidgetEngine widgetEngine) {
		this.widgetEngine = widgetEngine;
	}

    public void setWidgetFilters(List<WidgetFilter> widgetFilters) {
        this.widgetFilters = widgetFilters;
    }
	
	public void setPageConfigFactory(PageConfigFactory pageConfigFactory) {
		this.pageConfigFactory = pageConfigFactory;
	}
	
	public boolean matches(String requestPath) {
		PageConfig pageConfig = pageConfigFactory.findPage(requestPath);
		if(pageConfig!=null) {
			pageConfigLocal.set(pageConfig);
		}
		return pageConfigLocal.get()!=null;
	}
	
	public void doRender(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//
		PageConfig pageConfig = pageConfigLocal.get();
		//
		WidgetRequestFactory widgetRequestFactory = new WidgetRequestFactory(request);
		WidgetResponseFactory widgetResponseFactory = new WidgetResponseFactory(response);
		//
		Map<String, List<WidgetInvocation>> widgetInvocationMap = new HashMap<String, List<WidgetInvocation>>();
		Map<String, List<WidgetConfig>> widgetConfigMap = pageConfig.getWidgetConfigMap();
		for(String position : widgetConfigMap.keySet()) {
			//
			List<WidgetInvocation> widgetInvocationList = new ArrayList<WidgetInvocation>();
			List<WidgetConfig> widgetConfigList = widgetConfigMap.get(position);
            //
            for(WidgetFilter widgetFilter : widgetFilters) {
                widgetFilter.doFilter(request, response, widgetConfigList);
            }
            //
			for(WidgetConfig widgetConfig : widgetConfigList) {
				//
				WidgetRequest widgetRequest = widgetRequestFactory.createWidgetRequest(widgetConfig);
				WidgetResponse widgetResponse = widgetResponseFactory.createWidgetResponse();
				//
				WidgetInvocation widgetInvocation = new WidgetInvocation(widgetConfig, widgetRequest, widgetResponse);
				widgetInvocationList.add(widgetInvocation);
			}
			//
			widgetInvocationMap.put(position, widgetInvocationList);
			//
			widgetEngine.submit(widgetInvocationList);
		}
		//
		widgetEngine.waitForUntilException();
		//
		for(String position : widgetInvocationMap.keySet()) {
			StringBuilder sb = new StringBuilder();
            List<WidgetInvocation> widgetInvocations = widgetInvocationMap.get(position);
            for(WidgetInvocation widgetInvocation : widgetInvocations) {
                sb.append(widgetInvocation.getWidgetResponse().toString()).append("\n");
            }
            //
            request.setAttribute(position, sb.toString());
		}
	}

}
