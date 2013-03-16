package org.focusns.web.springmvc.bind;

/*
 * #%L
 * FocusSNS Web
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


import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.web.widget.annotation.WidgetAttribute;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

public class CustomWebArgumentResolver implements WebArgumentResolver {

    public Object resolveArgument(MethodParameter methodParameter,
                                  NativeWebRequest webRequest) throws Exception {
        //
        WidgetAttribute widgetAttribute = methodParameter.getParameterAnnotation(WidgetAttribute.class);
        if(widgetAttribute!=null) {
            String widgetAttributeName = getWidgetAttributeName(methodParameter, widgetAttribute);
            Object value = webRequest.getAttribute(widgetAttributeName, WebRequest.SCOPE_REQUEST);
            if(widgetAttribute.required()) {
                Assert.notNull(value, String.format("Widget attribute %s can not be null", value));
            }
            return value;
        }
        //
        return UNRESOLVED;
    }

    private String getWidgetAttributeName(MethodParameter methodParameter, WidgetAttribute widgetAttribute) {
        String widgetAttributeName = methodParameter.getParameterName();
        if(StringUtils.hasText(widgetAttribute.value())) {
            widgetAttributeName = widgetAttribute.value();
        } else if(ClassUtils.isAssignable(Project.class, methodParameter.getParameterType())) {
            widgetAttributeName = Project.KEY;
        } else if(ClassUtils.isAssignable(ProjectUser.class, methodParameter.getParameterType())) {
            widgetAttributeName = ProjectUser.KEY;
        }
        //
        return widgetAttributeName;
    }
}

