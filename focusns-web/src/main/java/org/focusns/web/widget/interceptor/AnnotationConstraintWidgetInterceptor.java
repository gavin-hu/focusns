package org.focusns.web.widget.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.focusns.common.web.page.config.PageConfig;
import org.focusns.web.widget.constraint.AnnotationConstraint;
import org.focusns.web.widget.constraint.PageAnnotationConstraint;
import org.focusns.web.widget.constraint.ProjectUserAnnotationConstraint;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AnnotationConstraintWidgetInterceptor extends HandlerInterceptorAdapter {
    private static final Log log = LogFactory.getLog(AnnotationConstraintWidgetInterceptor.class);
    //
    private List<AnnotationConstraint> annotationConstraints = new ArrayList<AnnotationConstraint>();

    public AnnotationConstraintWidgetInterceptor() {
        this.annotationConstraints.add(new ProjectUserAnnotationConstraint());
        this.annotationConstraints.add(new PageAnnotationConstraint());
    }

    public void setAnnotationConstraints(List<AnnotationConstraint> annotationConstraints) {
        this.annotationConstraints.addAll(annotationConstraints);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        NativeWebRequest webRequest = new ServletWebRequest(request);
        //
        if (WebUtils.isIncludeRequest(request)) {
            PageConfig pageConfig = (PageConfig) request.getAttribute("pageConfig");
            if (pageConfig!=null) {
                //
                for(AnnotationConstraint annotationConstraint : annotationConstraints) {
                    if(annotationConstraint.support(handler)) {
                        if(annotationConstraint.canHandle(webRequest, handler)==false) {
                            if(log.isDebugEnabled()) {
                                log.debug(String.format("Widget Handler [%s] 受到约束，将被忽略...", handler));
                            }
                            return false;
                        }
                    }
                }
            }
        }
        //
        return true;
    }

}
