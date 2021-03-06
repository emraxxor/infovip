package com.github.infovip.spring.controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.web.user.UserInfo;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {
	
	@Autowired
    private UserServiceInterface<User> userService;


	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/current")
	public Object current(HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model)  {
		if ( UserConfiguration.config(request).isAuthenticated() )  {
			User current = userService.findById(  UserConfiguration.config(request).getId() );
			return new UserInfo( current );
		}
		return null;
	}


}
