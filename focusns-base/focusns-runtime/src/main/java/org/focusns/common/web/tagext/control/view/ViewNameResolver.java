package org.focusns.common.web.tagext.control.view;

public interface ViewNameResolver {

    String resolveViewName(Class<?> componentClass, Object componentObject);

}
