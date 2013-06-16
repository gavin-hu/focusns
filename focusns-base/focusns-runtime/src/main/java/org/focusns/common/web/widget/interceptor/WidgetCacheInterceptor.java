package org.focusns.common.web.widget.interceptor;

import org.focusns.common.web.page.config.WidgetConfig;
import org.focusns.common.web.page.engine.widget.TimeoutWidgetCache;
import org.focusns.common.web.page.engine.widget.WidgetCache;
import org.focusns.common.web.page.engine.widget.WidgetRequest;
import org.focusns.common.web.page.engine.widget.WidgetResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WidgetCacheInterceptor extends HandlerInterceptorAdapter {

    private WidgetCache widgetCache = new TimeoutWidgetCache();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        if(request instanceof WidgetRequest && response instanceof WidgetResponse) {
            WidgetRequest widgetRequest = (WidgetRequest) request;
            WidgetResponse widgetResponse = (WidgetResponse) response;
            //
            if("render".equals(widgetRequest.getRequestType())) {
                WidgetConfig widgetConfig = widgetRequest.getWidgetConfig();
                WidgetResponse cachedWidgetResponse = (WidgetResponse) widgetCache.get(widgetConfig);
                if(cachedWidgetResponse!=null) {
                    FileCopyUtils.copy(cachedWidgetResponse.getResponseAsByteArray(), widgetResponse.getOutputStream());
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //
        if(request instanceof WidgetRequest && response instanceof WidgetResponse) {
            WidgetRequest widgetRequest = (WidgetRequest) request;
            WidgetResponse widgetResponse = (WidgetResponse) response;
            //
            if("render".equals(widgetRequest.getRequestType())) {
                WidgetConfig widgetConfig = widgetRequest.getWidgetConfig();
                if(widgetConfig.getCache()>0) {
                    widgetCache.put(widgetConfig, widgetResponse);
                }
            }
        }
    }
}
