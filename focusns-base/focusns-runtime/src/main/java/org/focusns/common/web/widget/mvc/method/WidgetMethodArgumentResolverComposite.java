package org.focusns.common.web.widget.mvc.method;

import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;

public class WidgetMethodArgumentResolverComposite extends HandlerMethodArgumentResolverComposite {

    public WidgetMethodArgumentResolverComposite() {
        addResolver(new WidgetMethodArgumentResolver());
        addResolver(new WidgetModelAttributeMethodProcessor());
    }
}
