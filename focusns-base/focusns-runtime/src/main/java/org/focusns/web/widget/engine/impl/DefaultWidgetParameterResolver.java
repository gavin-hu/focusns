package org.focusns.web.widget.engine.impl;

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


import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Bind.Scope;
import org.focusns.web.widget.engine.WidgetParameterResolver;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultWidgetParameterResolver implements WidgetParameterResolver{
	
	private ConversionService conversionService = new DefaultFormattingConversionService();

	public Object[] resolve(Method method, WidgetRequest request, WidgetResponse response) {
		List<Object> parameters = new ArrayList<Object>();
		Class<?>[] parameterTypes = method.getParameterTypes();
		Annotation[][] annotationArray = method.getParameterAnnotations();
		//
		int index = 0;
		for(Annotation[] annotations : annotationArray) {
			//
            boolean hasBindAnnotation = false;
			Object parameterValue = null;
			Class<?> parameterType = parameterTypes[index];
			for(Annotation annotation : annotations) {
				if(annotation instanceof Bind) {
					//
                    hasBindAnnotation = true;
					Bind bindAnnotation = (Bind) annotation;
					parameterValue = resolveBindParameter(request, parameterType, bindAnnotation);
				}
			}
			//
			if(parameterValue==null && !hasBindAnnotation) {
				parameterValue = resolveParameter(request, response, parameterType);
			}
            //
			parameters.add(parameterValue);
			//
			index++;	
	}
		//
		return parameters.toArray();
	}
	
	protected Object resolveParameter(WidgetRequest request, WidgetResponse response, 
			Class<?> parameterType) {
		if(parameterType.isAssignableFrom(WidgetRequest.class)) {
			return request;
		}
		if(parameterType.isAssignableFrom(WidgetResponse.class)) {
			return response;
		}
		if(parameterType.isAssignableFrom(Map.class)) {
			return request.getRequestAttributes();
		}
		//
		throw new IllegalArgumentException("WidgetParameterResolver can not resolve parameter type :" + parameterType);
	}
	
	protected Object resolveBindParameter(WidgetRequest request, Class<?> parameterType, 
			Bind bindAnnotation) {
		String name = bindAnnotation.value();
		Scope scope = bindAnnotation.scope();
        boolean required = bindAnnotation.required();
		//
		Object value = null;
		//
		if(Scope.PARAMETER==scope) {
			value = request.getRequestParameter(name);
		}
		if(Scope.REQUEST==scope) {
			value = request.getRequestAttribute(name);
		}
		if(Scope.SESSION==scope) {
			value = request.getSessionAttribute(name);
		}
		if(Scope.APPLICATION==scope) {
			value = request.getApplicationAttribute(name);
		}
        if(Scope.PREFERENCE==scope) {
            value = request.getWidgetPreference(name);
        }
		//
        if(value!=null) {
            return this.conversionService.convert(value, parameterType);
        }
        //
        if(value==null && required) {
            throw new IllegalArgumentException(String.format(
                    "WidgetParameterResolver can not resolve parameter type : %s", parameterType));
        }
        //
        if(parameterType.isPrimitive()) {
            if(ClassUtils.isAssignable(long.class, parameterType)
                    ||  ClassUtils.isAssignable(int.class, parameterType)
                    ||  ClassUtils.isAssignable(short.class, parameterType)) {
                return 0;
            }
            //
            if(ClassUtils.isAssignable(double.class, parameterType)
                    ||  ClassUtils.isAssignable(float.class, parameterType)) {
                return 0.0;
            }
        }
        return null;
	}

}
