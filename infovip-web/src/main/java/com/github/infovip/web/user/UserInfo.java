package com.github.infovip.web.user;

import com.github.infovip.core.data.DefaultUser;
import com.github.infovip.core.form.data.DefaultUserFormData;
import com.github.infovip.core.web.user.UserSessionInterface;
import com.github.infovip.entities.User;

/**
 * 
 * @author Attila Barna
 *
 */
public class UserInfo {

	private DefaultUserFormData data;
	
	public UserInfo(UserSessionInterface<User> session) {
		this.data = new DefaultUserFormData(session.getUser(), DefaultUser.class.getDeclaredFields() );
		this.data.setUserPassword("");
	}
	
	public DefaultUserFormData getData() {
		return data;
	}
	
	public void setData(DefaultUserFormData data) {
		this.data = data;
	}
	
}
