package org.focusns.common.web.tagext.control.view;

import org.springframework.util.ClassUtils;

public class ClassPathViewNameResolver implements ViewNameResolver {

    public String resolveViewName(Class<?> componentClass, Object componentObject) {
        return ClassUtils.convertClassNameToResourcePath(componentClass.getName());
    }
}
