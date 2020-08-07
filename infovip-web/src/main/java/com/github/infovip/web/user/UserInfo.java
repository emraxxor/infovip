package com.github.infovip.web.user;

import com.github.infovip.core.data.UserPublicFormElement;
import com.github.infovip.entities.User;

/**
 * 
 * @author Attila Barna
 *
 */
public class UserInfo {

	private UserPublicFormElement<User> data;
	
	
	public UserInfo(User u) {
		this.data = new UserPublicFormElement<User>(  u );
	}
	
	public  UserPublicFormElement<User> getData() {
		return data;
	}
	
	public void setData( UserPublicFormElement<User> data) {
		this.data = data;
	}
	
}
