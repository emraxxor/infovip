package com.github.infovip.core.web.user;

import java.util.Date;

public interface UserSessionInterface<T> {

	public Boolean isAuthenticated();
	
	public DefaultApplicationRole getRole();
	
	public String getUserMail();
	
	public Date getRegistrationDate();
	
	public Long userId();
	
	public T getUser();
}
