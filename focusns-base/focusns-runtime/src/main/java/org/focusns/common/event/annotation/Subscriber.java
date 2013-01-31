
package org.focusns.common.event.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Subscriber {
    
    String value() default "";
    
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface OnEvent {
        
        String value();
        
        boolean async() default true;
        
    }
    
}
