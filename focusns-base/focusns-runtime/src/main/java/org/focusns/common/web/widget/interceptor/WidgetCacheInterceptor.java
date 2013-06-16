package org.focusns.common.web.widget.interceptor;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.focusns.common.web.page.config.WidgetConfig;
import org.focusns.common.web.page.engine.widget.TimeoutWidgetCache;
import org.focusns.common.web.page.engine.widget.WidgetCache;
import org.focusns.common.web.page.engine.widget.WidgetRequest;
import org.focusns.common.web.page.engine.widget.WidgetResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WidgetCacheInterceptor extends HandlerInterceptorAdapter {

    private WidgetCache widgetCache = new TimeoutWidgetCache();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        if (request instanceof WidgetRequest && response instanceof WidgetResponse) {
            WidgetRequest widgetRequest = (WidgetRequest) request;
            WidgetResponse widgetResponse = (WidgetResponse) response;
            //
            if ("render".equals(widgetRequest.getRequestType())) {
                WidgetConfig widgetConfig = widgetRequest.getWidgetConfig();
                WidgetResponse cachedWidgetResponse = (WidgetResponse) widgetCache.get(widgetConfig);
                if (cachedWidgetResponse != null) {
                    FileCopyUtils.copy(cachedWidgetResponse.getResponseAsByteArray(), widgetResponse.getOutputStream());
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //
        if (request instanceof WidgetRequest && response instanceof WidgetResponse) {
            WidgetRequest widgetRequest = (WidgetRequest) request;
            WidgetResponse widgetResponse = (WidgetResponse) response;
            //
            if ("render".equals(widgetRequest.getRequestType())) {
                WidgetConfig widgetConfig = widgetRequest.getWidgetConfig();
                if (widgetConfig.getCache() > 0) {
                    widgetCache.put(widgetConfig, widgetResponse);
                }
            }
        }
    }
}
