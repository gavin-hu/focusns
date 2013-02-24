package org.focusns.web.widget.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.focusns.web.widget.engine.WidgetFilter;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeforeFilter {

    Class<? extends WidgetFilter> value();

}
