package org.focusns.web.widget.engine;

import java.lang.reflect.Method;
import java.util.List;

import org.focusns.web.widget.config.WidgetConfig;

public interface WidgetFilterResolver {

    List<WidgetFilter> resolve(Method method, WidgetConfig widgetConfig, Class<?> annotationType);

}
