package com.github.infovip.spring.components;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.github.infovip.core.web.user.CurrentUserInfo;
import com.github.infovip.core.web.user.DefaultApplicationRole;
import com.github.infovip.entities.User;
import com.github.infovip.spring.config.ApplicationUser;

/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class CurrentUserFacade implements CurrentUserInfo<User> {

	public CurrentUserFacade() {
		// TODO Auto-generated constructor stub
	}
	
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    private ApplicationUser user() {
    	return	 ( (ApplicationUser) getAuthentication().getPrincipal() );
    }

	
	@Override
	public Boolean isAuthenticated() {
		return getAuthentication() != null && user() != null  ;
	}

	@Override
	public DefaultApplicationRole getRole() {
		return user().getUserSession().getRole();
	}

	@Override
	public String getUserMail() {
		return user().getUser().getUserMail();
	}

	@Override
	public Date getRegistrationDate() {
		return user().getUser().getLogRegistration().getCreationTime();
	}

	@Override
	public String userName() {
		return user().getUser().getUserName();
	}

	@Override
	public Long userId() {
		return user().userId();
	}

	@Override
	public User getUser() {
		return user().getUser();
	}

}
