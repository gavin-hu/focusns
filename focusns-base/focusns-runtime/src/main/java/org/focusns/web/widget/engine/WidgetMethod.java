
package org.focusns.web.widget.engine;

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


import org.focusns.web.widget.annotation.Resource;
import org.focusns.web.widget.annotation.Resources;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WidgetMethod {
    
    private Method method;

    public WidgetMethod(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    public List<Resource> getMethodResources() {
        List<Resource> resourceList = new ArrayList<Resource>();
        Resource resource = AnnotationUtils.getAnnotation(method, Resource.class);
        if(resource!=null) {
            resourceList.add(resource);
        }
        //
        Resources resources = AnnotationUtils.getAnnotation(method, Resources.class);
        if(resources!=null) {
            resourceList.addAll(Arrays.asList(resources.value()));
        }
        //
        return resourceList;
    }
    
}
