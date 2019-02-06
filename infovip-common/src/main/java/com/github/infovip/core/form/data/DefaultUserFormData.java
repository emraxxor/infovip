package com.github.infovip.core.form.data;

import java.lang.reflect.Field;

import com.github.infovip.core.data.DefaultUser;


/**
 * 
 * @author Attila Barna
 *
 */
public class DefaultUserFormData<T extends UserIface<TLOG>, TLOG extends LogIFace<T>> extends DefaultUser<T> {

	public DefaultUserFormData() {
		super();
	}
	
	public DefaultUserFormData(T u) {
		super(u);
	}
	
	public DefaultUserFormData(T u, Field[] fields) {
		super(u,fields);
	}

	
	public T toUser(Class<T> user, Class<TLOG> clog) {
		try {
			TLOG log = clog.newInstance();
			T u =  toDataElement(user , DefaultUser.class.getDeclaredFields());
			log.setUid(u);
			u.setLogRegistration(log);
			return u;
		} catch (InstantiationException | IllegalAccessException | SecurityException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
