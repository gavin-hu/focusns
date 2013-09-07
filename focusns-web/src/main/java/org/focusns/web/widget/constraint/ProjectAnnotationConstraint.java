package org.focusns.web.widget.constraint;

import org.focusns.web.Keys;
import org.focusns.web.widget.constraint.annotation.NotRequiresProject;
import org.focusns.web.widget.constraint.annotation.RequiresProject;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.lang.annotation.Annotation;

public class ProjectAnnotationConstraint extends AbstractAnnotationConstraint {

    public ProjectAnnotationConstraint() {
        super(RequiresProject.class, NotRequiresProject.class);
    }

    @Override
    protected boolean canHandle(NativeWebRequest webRequest, Annotation[] annotations) {
        //
        for(Annotation annotation : annotations) {
            //
            if(ClassUtils.isAssignableValue(RequiresProject.class, annotation)) {
                return webRequest.getAttribute(Keys.REQUEST_PROJECT, WebRequest.SCOPE_REQUEST)!=null;
            } else if(ClassUtils.isAssignableValue(NotRequiresProject.class, annotation)) {
                return webRequest.getAttribute(Keys.REQUEST_PROJECT, WebRequest.SCOPE_REQUEST)==null;
            }
        }
        //
        return false;
    }
}
