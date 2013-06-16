package org.focusns.common.web.widget.mvc.method;

import org.springframework.core.MethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.validation.groups.Default;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class WidgetModelAttributeMethodProcessor extends ServletModelAttributeMethodProcessor {

    private ThreadLocal<NativeWebRequest> webRequestLocal = new ThreadLocal<NativeWebRequest>();

    public WidgetModelAttributeMethodProcessor() {
        super(true);
    }



    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        super.bindRequestParameters(binder, request);
        //
        webRequestLocal.set(request);
    }

    @Override
    protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
        //
        WebRequest webRequest = webRequestLocal.get();
        String groupsStr = webRequest.getParameter("groups");
        if(StringUtils.hasText(groupsStr)) {
            List<Object> hintList = new ArrayList<Object>();
            String[] groups = StringUtils.commaDelimitedListToStringArray(groupsStr);
            for(String group : groups) {
                try {
                    hintList.add(ClassUtils.forName(group, getClass().getClassLoader()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //
            hintList.add(Default.class);
            //
            Annotation[] annotations = parameter.getParameterAnnotations();
            for (Annotation annot : annotations) {
                if (annot.annotationType().getSimpleName().startsWith("Valid")) {
                    Object hints = hintList.toArray(new Object[hintList.size()]);
                    binder.validate(hints instanceof Object[] ? (Object[]) hints : new Object[] {hints});
                }
            }
        }
    }
}
