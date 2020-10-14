package com.github.infovip.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.infovip.entities.User;
import com.github.infovip.spring.config.ApplicationUser;
import com.github.infovip.spring.config.ApplicationUserRole;
import com.github.infovip.spring.repositories.UserRepository;

/**
 * 
 * @author Attila Barna
 *
 */
@Service
public class ApplicationUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder pw;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUserMail(username);
		return new ApplicationUser(
				ApplicationUserRole.USER.grantedAuthorities(),
				u
		);
	}

}
