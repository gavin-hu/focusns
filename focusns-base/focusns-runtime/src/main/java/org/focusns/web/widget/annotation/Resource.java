
package org.focusns.web.widget.annotation;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Resource {

    public enum Scope {
		PARAMETER, REQUEST, SESSION, APPLICATION
	}
    
    String[] required();
    
    Scope scope() default Scope.PARAMETER;
    
}
