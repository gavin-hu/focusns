package org.focusns.web.widget.constraint;

import org.springframework.web.context.request.NativeWebRequest;

public interface AnnotationConstraint {

    boolean support(Object handler);

    boolean canHandle(NativeWebRequest webRequest, Object handler);

}
