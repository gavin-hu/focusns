package org.focusns.common.web.widget.mvc.method;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.validation.groups.Default;

import org.springframework.core.MethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

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
        if (StringUtils.hasText(groupsStr)) {
            List<Object> hintList = new ArrayList<Object>();
            String[] groups = StringUtils.commaDelimitedListToStringArray(groupsStr);
            for (String group : groups) {
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
                    binder.validate(hints instanceof Object[] ? (Object[]) hints : new Object[] { hints });
                }
            }
        }
    }
}
