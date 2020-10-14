package com.github.infovip.spring.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.infovip.core.web.user.UserSession;
import com.github.infovip.core.web.user.WebUser;
import com.github.infovip.entities.User;

/**
 * 
 * @author Attila Barna
 *
 */
public class ApplicationUser implements UserDetails, WebUser {

	private final List<? extends GrantedAuthority> grantedAuthorities;
	
	private final String username;
	
	private final Long userId;
	
	private final String password;
	
	private final Boolean isAccountNonExpired;
	
	private final Boolean isAaccountNonLocked;
	
	private final Boolean isCredentialsNonExpired;
	
	private final Boolean isEnabled;
	
	private final User user;
	
	private UserSession userSession;
	
	
	public ApplicationUser(List<? extends GrantedAuthority> grantedAuthorities, User u) {
		super();
		this.grantedAuthorities = grantedAuthorities;
		this.userId = u.getUserId();
		this.username = u.getUserMail();
		this.password = u.getUserPassword();
		this.isAccountNonExpired = u.getIsActive();
		this.isAaccountNonLocked = u.getIsActive();
		this.isCredentialsNonExpired = u.getIsActive();
		this.isEnabled = u.getIsActive();
		this.user = u;
	}

	public User getUser() {
		return user;
	}
	
	public UserSession getUserSession() {
		return userSession;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAaccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	@Override
	public Long userId() {
		return this.userId;
	}

	@Override
	public String userIdentifier() {
		return this.getUser().getUserName();
	}

	@Override
	public String userMailAddress() {
		return this.getUser().getUserMail();
	}
	
	
}
