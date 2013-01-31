
package org.focusns.common.event.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Triggers {
    
    Trigger[] value();
    
}
