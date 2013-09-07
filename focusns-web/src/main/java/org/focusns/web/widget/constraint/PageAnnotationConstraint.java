package org.focusns.web.widget.constraint;

import org.focusns.web.widget.constraint.annotation.NotRequiresPage;
import org.focusns.web.widget.constraint.annotation.RequiresPage;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.lang.annotation.Annotation;

public class PageAnnotationConstraint extends AbstractAnnotationConstraint {

    public PageAnnotationConstraint() {
        super(RequiresPage.class, NotRequiresPage.class);
    }

    @Override
    protected boolean canHandle(NativeWebRequest webRequest, Annotation[] annotations) {
        //
        for(Annotation annotation : annotations) {
            //
            if(ClassUtils.isAssignableValue(RequiresPage.class, annotation)) {
                RequiresPage requiresPage = (RequiresPage) annotation;
                return webRequest.getAttribute(requiresPage.value(), WebRequest.SCOPE_REQUEST)!=null;
            } else if(ClassUtils.isAssignableValue(NotRequiresPage.class, annotation)) {
                NotRequiresPage noteRequiresPage = (NotRequiresPage) annotation;
                return webRequest.getAttribute(noteRequiresPage.value(), WebRequest.SCOPE_REQUEST)!=null;
            }
        }
        //
        return false;
    }

}
