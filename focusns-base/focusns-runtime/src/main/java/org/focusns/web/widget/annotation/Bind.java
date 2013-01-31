
package org.focusns.web.widget.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bind {

	public enum Scope {
		PARAMETER, REQUEST, SESSION, APPLICATION, PREFERENCE
	}
	
	String value();
    
    boolean required() default false;
	
	Scope scope() default Scope.PARAMETER;
	
}
