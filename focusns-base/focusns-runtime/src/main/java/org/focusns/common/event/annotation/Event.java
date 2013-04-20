package org.focusns.common.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Event {

    String on();

    Point point();

    boolean async() default true;

    public enum Point {
        BEFORE, AFTER, AFTER_THROWING
    }

}