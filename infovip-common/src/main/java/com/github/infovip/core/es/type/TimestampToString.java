package com.github.infovip.core.es.type;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.infovip.core.date.DefaultDateFormatter;

/**
 * 
 * @author Attila Barna
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface TimestampToString {

	DefaultDateFormatter.DATE_FORMAT type() default DefaultDateFormatter.DATE_FORMAT.STRICT_DATE_FORMAT;
}
