package org.focusns.web.widget.constraint;

import java.lang.annotation.Annotation;

public interface AnnotationResolver {

    boolean isPresent(Object handler, Class<? extends Annotation>[] annotationTypes);

    Annotation[] getAnnotations(Object handler, Class<? extends Annotation>[] annotationTypes);

}
