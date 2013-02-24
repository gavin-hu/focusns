package org.focusns.web.widget.engine.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.focusns.web.widget.annotation.AfterFilter;
import org.focusns.web.widget.annotation.BeforeFilter;
import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.engine.WidgetFilter;
import org.focusns.web.widget.engine.WidgetFilterResolver;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

public class DefaultWidgetFilterResolver implements WidgetFilterResolver {

    private Map<Method, List<WidgetFilter>> beforeFilterCache = new HashMap<Method, List<WidgetFilter>>();
    private Map<Method, List<WidgetFilter>> afterFilterCache = new HashMap<Method, List<WidgetFilter>>();

    public List<WidgetFilter> resolve(Method method, WidgetConfig widgetConfig, Class<?> annotationType) {
        List<WidgetFilter> widgetFilters = null;
        //
        if(ClassUtils.isAssignable(BeforeFilter.class, annotationType)) {
            widgetFilters = beforeFilterCache.get(method);
            //
            if(widgetFilters==null) {
                widgetFilters = new ArrayList<WidgetFilter>();
            }
            //
            BeforeFilter beforeFilter = AnnotationUtils.findAnnotation(method, BeforeFilter.class);
            if(beforeFilter!=null) {
                WidgetFilter widgetFilter = BeanUtils.instantiateClass(beforeFilter.value(), WidgetFilter.class);
                widgetFilters.add(widgetFilter);
            }
            //
            afterFilterCache.put(method, widgetFilters);
        }
        //
        if(ClassUtils.isAssignable(AfterFilter.class, annotationType)) {
            widgetFilters = afterFilterCache.get(method);
            //
            if(widgetFilters==null) {
                widgetFilters = new ArrayList<WidgetFilter>();
            }
            AfterFilter afterFilter = AnnotationUtils.findAnnotation(method, AfterFilter.class);
            if(afterFilter!=null) {
                WidgetFilter widgetFilter = BeanUtils.instantiateClass(afterFilter.value(), WidgetFilter.class);
                widgetFilters.add(widgetFilter);
            }
            //
            afterFilterCache.put(method, widgetFilters);
        }
        //
        return widgetFilters;
    }
}
