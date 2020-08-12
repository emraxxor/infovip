package com.github.infovip.core.web.types;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

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
	
	
	@IgnoreField
	private static transient Logger FormElementLogger = Logger.getLogger(FormElement.class);

	
	public FormElement() { }

	public FormElement(T data) {
		this.data = data;
		Class<?> o = data.getClass(); 
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
	
	public FormElement(T data, Field[] fields) {
		this.data = data;
		Class<?> o = data.getClass(); 
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

	
	public T toDataElement(Class<T> clazz,Field[] fields) {
		Constructor<?> cons = null;
		T object = null;
		try {
			cons = clazz.getConstructor();
			object = (T) cons.newInstance();
			
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

	
	public T toDataElement(Class<T> clazz) {
		return this.toDataElement(clazz,  this.getClass().getDeclaredFields());
	}
	
	public T original() {
		return this.data;
	}
	
	public static <Y,Z>  Z convertTo(Y from, Class<Z> to) {
		Constructor<?> cons = null;
		Z object = null;
		Field[] fields = to.getDeclaredFields();

		try {
			cons = to.getConstructor();
			object = (Z) cons.newInstance();
			
			for(Field f : fields ) {
				if ( f.getAnnotation(IgnoreField.class)  != null ) continue;
				Method s = to.getMethod("set" + StringUtils.capitalize(f.getName()) , f.getType());
				s.invoke(object, from.getClass().getMethod("get" + StringUtils.capitalize(f.getName())).invoke(from) );
			}
			
		} catch (NoSuchMethodException | SecurityException e) {
			FormElementLogger.error(e);
		} catch (InstantiationException e) {
			FormElementLogger.error(e);
		} catch (IllegalAccessException e) {
			FormElementLogger.error(e);
		} catch (IllegalArgumentException e) {
			FormElementLogger.error(e);
		} catch (InvocationTargetException e) {
			FormElementLogger.error(e);
		}
		
		return object;

	}
	
	public static <Y,Z>  void update(Y from, Z to) {
		Field[] fields = to.getClass().getDeclaredFields();
		try {
			for(Field f : fields ) {
				if ( f.getAnnotation(IgnoreField.class)  != null ) continue;
				Method s = to.getClass().getMethod("set" + StringUtils.capitalize(f.getName()) , f.getType());
				s.invoke(to, from.getClass().getMethod("get" + StringUtils.capitalize(f.getName())).invoke(from) );
			}
		} catch (NoSuchMethodException | SecurityException e) {
			FormElementLogger.error(e);
		} catch (IllegalAccessException e) {
			FormElementLogger.error(e);
		} catch (IllegalArgumentException e) {
			FormElementLogger.error(e);
		} catch (InvocationTargetException e) {
			FormElementLogger.error(e);
		}
	}
}

