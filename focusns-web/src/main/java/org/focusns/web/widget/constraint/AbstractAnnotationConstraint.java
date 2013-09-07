package org.focusns.web.widget.constraint;

import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.NativeWebRequest;

import java.lang.annotation.Annotation;

public abstract class AbstractAnnotationConstraint implements AnnotationConstraint {

    private Class<? extends Annotation>[] annotationTypes = new Class[1];
    //
    private AnnotationResolver annotationResolver = new DefaultAnnotationResolver();

    public AbstractAnnotationConstraint(Class<? extends Annotation> annotationType, Class<? extends Annotation>... moreAnnotationTypes) {
        annotationTypes[0] = annotationType;
        for(int i=0; i<moreAnnotationTypes.length; i++) {
            ObjectUtils.addObjectToArray(annotationTypes, moreAnnotationTypes[i]);
        }
    }

    public AnnotationResolver getAnnotationResolver() {
        return annotationResolver;
    }

    /**
     * 默认使用 DefaultAnnotationResolver， 可以通过此方法注入额外的实现
     *
     * @param annotationResolver
     */
    public void setAnnotationResolver(AnnotationResolver annotationResolver) {
        this.annotationResolver = annotationResolver;
    }

    @Override
    public boolean support(Object handler) {
        for(Class<?> annotationType : annotationTypes) {
            if(getAnnotationResolver().isPresent(handler, annotationTypes)) {
                return true;
            }
        }
        //
        return false;
    }

    @Override
    public boolean canHandle(NativeWebRequest webRequest, Object handler) {
        //
        Annotation[] annotations = getAnnotationResolver().getAnnotations(handler, annotationTypes);
        //
        return canHandle(webRequest, annotations);
    }

    protected abstract boolean canHandle(NativeWebRequest webRequest, Annotation[] annotations) ;

}
