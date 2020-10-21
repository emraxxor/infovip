package com.github.infovip.configuration;

import javax.servlet.http.HttpServletRequest;

import com.github.infovip.core.session.DefaultUserSession;
import com.github.infovip.core.web.user.CurrentUserInfo;
import com.github.infovip.core.web.user.WebUser;

/**
 * 
 * @author Attila Barna
 *
 */
public class UserConfiguration {

	protected HttpServletRequest request;

	public UserConfiguration(HttpServletRequest request) {
		this.request = request;
	}

	public static UserConfiguration config(HttpServletRequest request) {
		return new UserConfiguration(request);
	}
	
	public String userIdentifier() {
		try {
			if (request.getSession().getAttribute(DefaultUserSession.USER_SESSION.value()) != null) {
				WebUser u = (WebUser) request.getSession().getAttribute(DefaultUserSession.USER_SESSION.value());
				return u.userIdentifier();
			}
		} catch (Exception e) {
			throw new RuntimeException("Not supported session!");
		}
		return null;
	}

	public Long getId() {
		try {
			if (request.getSession().getAttribute(DefaultUserSession.USER_SESSION.value()) != null) {
				WebUser u = (WebUser) request.getSession().getAttribute(DefaultUserSession.USER_SESSION.value());
				return u.userId();
			}
		} catch (Exception e) {
			throw new RuntimeException("Not supported session!");
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public CurrentUserInfo getSession() {
		if (request.getSession().getAttribute(DefaultUserSession.USER_SESSION.value()) != null) 
			return (CurrentUserInfo) request.getSession().getAttribute(DefaultUserSession.USER_SESSION.value()) ;
		
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Boolean isAuthenticated() {
		try {
			if (request.getSession().getAttribute(DefaultUserSession.USER_SESSION.value()) != null) {
				return ((CurrentUserInfo)request.getSession().getAttribute(DefaultUserSession.USER_SESSION.value())).isAuthenticated();
			}
		} catch (Exception e) {
			throw new RuntimeException("Not supported session!");
		}
		return false;
	}

}
