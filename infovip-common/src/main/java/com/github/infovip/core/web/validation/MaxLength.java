package com.github.infovip.core.web.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Attila Barna
 * @category infovip.web.validation
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface MaxLength {

	String message() default "";
	int size();
}
