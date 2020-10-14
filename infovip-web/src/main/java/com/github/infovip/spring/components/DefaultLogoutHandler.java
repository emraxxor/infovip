package com.github.infovip.spring.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.core.log.BaseLogDataElement;
import com.github.infovip.core.log.BaseLogElement;
import com.github.infovip.core.log.LogManager;
import com.github.infovip.core.log.LogType;
import com.github.infovip.entities.User;
import com.github.infovip.spring.config.ApplicationUser;
import com.github.infovip.spring.controllers.AuthenticationController;

@Component
public class DefaultLogoutHandler implements LogoutHandler {

	@Autowired
	private LogManager<BaseLogElement<?>, WebApplicationContext, HttpServletRequest> logManager;

 
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        ApplicationUser au = (ApplicationUser)authentication.getPrincipal();
        User u = au.getUser();
    	logManager.synch(
				logManager
				.create( new BaseLogDataElement() , request)
				.user( u.getUserName() )
				.type(LogType.AUTH)
				.component(AuthenticationController.class.getName())
				.message("Successfully logged out of the system.")
				.getLogElement()
		);

		
	}

}
