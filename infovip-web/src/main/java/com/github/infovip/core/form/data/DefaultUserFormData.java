package com.github.infovip.core.form.data;

import com.github.infovip.core.data.DefaultUser;
import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;


/**
 * 
 * @author Attila Barna
 *
 */
public class DefaultUserFormData extends DefaultUser<User> {

	public DefaultUserFormData() {
		super();
	}
	
	public DefaultUserFormData(User u) {
		super(u);
	}
	
	public User toUser() {
		LogRegistration log = new LogRegistration();
		User u = toDataElement(User.class, DefaultUser.class.getDeclaredFields());
		log.setUid(u);
		u.setLogRegistration(log);
		return u;
	}
}
