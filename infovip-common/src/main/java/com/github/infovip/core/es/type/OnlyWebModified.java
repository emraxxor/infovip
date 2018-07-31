package com.github.infovip.core.es.type;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * OnlyWebModified interface indicates whether the document can be modified via only web interface.
 * @author Attila Barna
 *
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface OnlyWebModified {

}

