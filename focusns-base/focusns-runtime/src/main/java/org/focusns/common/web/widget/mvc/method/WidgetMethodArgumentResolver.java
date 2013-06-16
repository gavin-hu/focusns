package org.focusns.common.web.widget.mvc.method;

import org.focusns.common.web.page.config.WidgetConfig;
import org.focusns.common.web.widget.annotation.bind.WidgetAttribute;
import org.focusns.common.web.widget.annotation.bind.WidgetPref;
import org.focusns.common.web.widget.mvc.support.Navigator;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class WidgetMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter parameter) {
        //
        if(parameter.hasParameterAnnotation(WidgetAttribute.class)) {
            return true;
        } else if(parameter.hasParameterAnnotation(WidgetPref.class)) {
            return true;
        }
        //
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //
        Object value = null;
        if(parameter.hasParameterAnnotation(WidgetAttribute.class)) {
            value = getWidgetAttributeValue(parameter, webRequest);
        } else if(parameter.hasParameterAnnotation(WidgetPref.class)) {
            value = getWidgetPrefValue(parameter, webRequest);
        }
        //
        if(value!=null) {
            ConversionService conversionService = (ConversionService) webRequest
                    .getAttribute(ConversionService.class.getName(), WebRequest.SCOPE_REQUEST);
            if(conversionService.canConvert(value.getClass(), parameter.getParameterType())) {
                value = conversionService.convert(value, parameter.getParameterType());
            } else {
                throw new ConverterNotFoundException(TypeDescriptor.forObject(value),
                        TypeDescriptor.valueOf(parameter.getParameterType()));
            }
        }
        //
        return value;
    }

    private String getWidgetAttributeName(MethodParameter methodParameter, WidgetAttribute widgetAttribute) {
        String widgetAttributeName = methodParameter.getParameterName();
        if (StringUtils.hasText(widgetAttribute.value())) {
            widgetAttributeName = widgetAttribute.value();
        }
        //
        return widgetAttributeName;
    }

    private Object getWidgetAttributeValue(MethodParameter parameter, NativeWebRequest webRequest) {
        Class<?> widgetClass = parameter.getMethod().getDeclaringClass();
        WidgetAttribute widgetAttribute = parameter.getParameterAnnotation(WidgetAttribute.class);
        if (widgetAttribute != null) {
            String widgetAttributeName = getWidgetAttributeName(parameter, widgetAttribute);
            Object value = webRequest.getAttribute(widgetAttributeName, WebRequest.SCOPE_REQUEST);
            if (widgetAttribute.required()) {
                Assert.notNull(value, String.format("%s attribute %s can not be null!", widgetClass, widgetAttributeName));
            }
            return value;
        }
        //
        return null;
    }

    private Object getWidgetPrefValue(MethodParameter methodParameter, NativeWebRequest webRequest) {
        WidgetPref widgetPreference = methodParameter.getParameterAnnotation(WidgetPref.class);
        if (widgetPreference != null) {
            WidgetConfig widgetConfig = (WidgetConfig) webRequest.getAttribute("widgetConfig", WebRequest.SCOPE_REQUEST);
            String widgetPreferenceName = getWidgetPreferenceName(methodParameter, widgetPreference);
            if (widgetConfig != null) {
                String defaultValue = widgetPreference.defaultValue();
                Object value = widgetConfig.getPreferences().get(widgetPreferenceName);
                if (StringUtils.hasText(defaultValue) && value == null) {
                    value = defaultValue;
                }
                //
                if (widgetPreference.required()) {
                    Assert.notNull(value, String.format("Widget preference %s can not be null!", value));
                }
                //
                return value;
            }
        }
        //
        return null;
    }

    private String getWidgetPreferenceName(MethodParameter methodParameter, WidgetPref widgetPreference) {
        String widgetPreferenceName = methodParameter.getParameterName();
        //
        if (StringUtils.hasText(widgetPreference.value())) {
            widgetPreferenceName = widgetPreference.value();
        }
        //
        return widgetPreferenceName;
    }
}
