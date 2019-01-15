package com.github.infovip.core.web.types;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.annotations.Expose;

import com.github.infovip.core.es.type.IgnoreField;

/**
 * 
 * @author Attila Barna
 * @category infovip.form
 *
 * @param <T>
 */
public class FormElement<T> implements FormData {

	@IgnoreField
	protected transient T data;
	
	@IgnoreField
	protected transient Logger logger = Logger.getLogger(this.getClass());
	
	
	public FormElement() {
	}

	public FormElement(T data) {
		this.data = data;
		Class o = data.getClass(); 
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for(Field f : fields ) {
				if ( f.getAnnotation(IgnoreField.class)  != null ) continue;
				
				Method s = this.getClass().getMethod("set" + StringUtils.capitalize(f.getName()) , f.getType());
				s.invoke(this,  o.getMethod("get" + StringUtils.capitalize(f.getName())).invoke(data) );
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
	
	public T toDataElement(Class<T> clazz) {
		Constructor<?> cons = null;
		T object = null;
		try {
			cons = clazz.getConstructor();
			object = (T) cons.newInstance();
			Field[] fields = this.getClass().getDeclaredFields();

			for(Field f : fields ) {
				if ( f.getAnnotation(IgnoreField.class)  != null ) continue;
				
				Method s = clazz.getMethod("set" + StringUtils.capitalize(f.getName()) , f.getType());
				s.invoke(object,  this.getClass().getMethod("get" + StringUtils.capitalize(f.getName())).invoke(this) );
			}
			
		} catch (NoSuchMethodException | SecurityException e) {
			logger.error(e);
		} catch (InstantiationException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		}
		return object;
	}
	
	
	public T original() {
		return this.data;
	}
}

