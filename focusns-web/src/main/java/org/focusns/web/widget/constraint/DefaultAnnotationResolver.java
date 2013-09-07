package org.focusns.web.widget.constraint;

import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class DefaultAnnotationResolver implements AnnotationResolver {

    @Override
    public boolean isPresent(Object handler, Class<? extends Annotation>[] annotationTypes) {
        //
        if(handler instanceof HandlerMethod) {
            for(Class<? extends Annotation> annotationType : annotationTypes) {
                if(((HandlerMethod)handler).getMethodAnnotation(annotationType)!=null) {
                    return true;
                }
            }
            return false;
        }
        //
        return false;
    }

    @Override
    public Annotation[] getAnnotations(Object handler, Class<? extends Annotation>[] annotationTypes) {
        //
        if(handler instanceof HandlerMethod) {
            List<Annotation> annotationList = new ArrayList<Annotation>();
            for(Class<? extends Annotation> annotationType : annotationTypes) {
                Annotation annotation =((HandlerMethod)handler).getMethodAnnotation(annotationType);
                if(annotation!=null) {
                    annotationList.add(annotation);
                }
            }
            return annotationList.toArray(new Annotation[annotationList.size()]);
        }
        //
        return new Annotation[0];
    }

}
