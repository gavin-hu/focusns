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

package org.focusns.web.widget.engine.impl;

import org.focusns.web.widget.annotation.AfterFilter;
import org.focusns.web.widget.annotation.BeforeFilter;
import org.focusns.web.widget.config.WidgetConfig;
import org.focusns.web.widget.engine.WidgetFilter;
import org.focusns.web.widget.engine.WidgetFilterResolver;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultWidgetFilterResolver implements WidgetFilterResolver {

    private Map<Method, List<WidgetFilter>> beforeFilterCache = new HashMap<Method, List<WidgetFilter>>();
    private Map<Method, List<WidgetFilter>> afterFilterCache = new HashMap<Method, List<WidgetFilter>>();

    @Override
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
