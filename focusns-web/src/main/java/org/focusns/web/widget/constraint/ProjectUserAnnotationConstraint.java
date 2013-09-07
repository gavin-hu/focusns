package org.focusns.web.widget.constraint;

import org.focusns.web.Keys;
import org.focusns.web.widget.constraint.annotation.NotRequiresProjectUser;
import org.focusns.web.widget.constraint.annotation.RequiresProjectUser;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.lang.annotation.Annotation;

public class ProjectUserAnnotationConstraint extends AbstractAnnotationConstraint {

    public ProjectUserAnnotationConstraint() {
        super(RequiresProjectUser.class, NotRequiresProjectUser.class);
    }

    @Override
    protected boolean canHandle(NativeWebRequest webRequest, Annotation[] annotations) {
        //
        for(Annotation annotation : annotations) {
            //
            if(ClassUtils.isAssignableValue(RequiresProjectUser.class, annotation)) {
                return webRequest.getAttribute(Keys.REQUEST_PROJECT_USER, WebRequest.SCOPE_REQUEST)!=null;
            } else if(ClassUtils.isAssignableValue(NotRequiresProjectUser.class, annotation)) {
                return webRequest.getAttribute(Keys.REQUEST_PROJECT_USER, WebRequest.SCOPE_REQUEST)==null;
            }
        }
        //
        return false;
    }
}
