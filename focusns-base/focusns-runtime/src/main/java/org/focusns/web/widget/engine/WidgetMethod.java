
package org.focusns.web.widget.engine;

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
