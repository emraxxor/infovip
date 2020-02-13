package com.github.infovip.core.web.types;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

/**
 * It helps to manage the forms of the application.
 * By default it is created for the client framework manager which can handle the data-objects correctly.
 * 
 * @author Attila Barna
 * @category infovip.form
 *
 */
public class FormManager {

	private static final Logger logger = Logger.getLogger(FormManager.class);
	
	/**
	 * Basically it converts a data-object to a displayable form-element which is processed by the client-ui framework.
	 * @param from
	 * @param to
	 */
	public static <DB_DATA, CLIENT_DATA extends FormElement<DB_DATA>> List<CLIENT_DATA> convertDataListToFormElement(List<DB_DATA> from, Class<DB_DATA> dd,  Class<CLIENT_DATA> to) {
		if ( from == null) 
			return new ArrayList<>();
		
		
		return from.stream().map(o ->  {  
								CLIENT_DATA cd = null;
								try {
									cd = to.getConstructor(dd).newInstance(o);
								} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
										| NoSuchMethodException | SecurityException e) {
										logger.error(e);
								}
								return cd;  
			} ).collect(Collectors.toList());
	}
}
