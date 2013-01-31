

package org.focusns.web.widget.engine;

import org.focusns.web.widget.WidgetRequest;
import org.focusns.web.widget.WidgetResponse;
import org.focusns.web.widget.config.WidgetConfig;

import java.lang.reflect.Method;
import java.util.List;

public interface WidgetFilterResolver {

    List<WidgetFilter> resolve(Method method, WidgetConfig widgetConfig, Class<?> annotationType);

}
