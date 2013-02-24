package org.focusns.web.page.render.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.focusns.web.page.config.PageConfig;
import org.focusns.web.page.config.PageConfigFactory;
import org.focusns.web.page.render.PageRender;
import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetRequestFactory;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.WidgetResponseFactory;
import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.engine.WidgetEngine;
import org.focusns.web.widget.engine.WidgetInvocation;

public class StandardPageRender implements PageRender {

	private WidgetEngine widgetEngine;
	
	private PageConfigFactory pageConfigFactory;
	
	private ThreadLocal<PageConfig> pageConfigLocal = new ThreadLocal<PageConfig>();
	
	public StandardPageRender() {
	}
	
	public void setWidgetEngine(WidgetEngine widgetEngine) {
		this.widgetEngine = widgetEngine;
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
            List<WidgetInvocation> widgetInvocations = widgetInvocationMap.get(position);
            //
            boolean skipPosition = true;
            StringBuilder sb = new StringBuilder();
            for(WidgetInvocation widgetInvocation : widgetInvocations) {
                WidgetResponse widgetResonse = widgetInvocation.getWidgetResponse();
                if(widgetResonse.isCommitted()) {
                    skipPosition = false;
                    sb.append(widgetInvocation.getWidgetResponse().toString()).append("\n");
                }
            }
            // skip position ?
            if(!skipPosition) {
                request.setAttribute(position, sb);
            }
		}
	}

}
