package com.github.infovip.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.github.infovip.core.web.user.CurrentWebUserSession;

import lombok.Synchronized;


/**
 * 
 * @author Attila Barna
 *
 */
public class CurrentUser {
	
	@Synchronized
	public static Long id() {
		return principal().current().userId();
	}

	@Synchronized
	public static  CurrentWebUserSession<?> principal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (CurrentWebUserSession<?>) authentication.getPrincipal();
	}
}
