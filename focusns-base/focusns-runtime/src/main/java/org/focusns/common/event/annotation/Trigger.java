
package org.focusns.common.event.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Trigger {
    
    String event();
    
    Point point();

    public enum Point {
        BEFORE, AFTER, AFTER_THROWING
    }
            
}
