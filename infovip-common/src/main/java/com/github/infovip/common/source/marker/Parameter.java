package com.github.infovip.common.source.marker;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
/**
 * Specifies the name of the paramater
 * @author attila
 *
 */
public @interface Parameter {
	String name();
	Class<?> type();
}
