package com.github.infovip.core.es.type.validator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.github.infovip.core.es.type.DefaultDocumentType;

public class DocumentValidator {
	
	public static Logger logger = Logger.getLogger(DocumentValidator.class);

	
	public DocumentValidator() {
	}
	
	public static <T> boolean validate(T o) {
		if ( o instanceof DefaultDocumentType ) {
			// check empty fields (only @RequiredField annotation)
			Field[] fields = o.getClass().getDeclaredFields();
			for(Field f : fields) {
				if ( f.getName().equalsIgnoreCase("serialversionuid") )
					continue; 
				
				if ( f.getAnnotation(RequiredField.class) != null ) {
					try {
						Method m = o.getClass().getMethod("get" + StringUtils.capitalize(f.getName()));
						Object elem = m.invoke(o);
						if ( elem == null || ( elem instanceof String && elem.equals("") ) ) {
							return false;
						}
					} catch (NoSuchMethodException e) {
						logger.error(e);
					} catch (SecurityException e) {
						logger.error(e);
					} catch (IllegalAccessException e) {
						logger.error(e);
					} catch (IllegalArgumentException e) {
						logger.error(e);
					} catch (InvocationTargetException e) {
						logger.error(e);
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	
}
