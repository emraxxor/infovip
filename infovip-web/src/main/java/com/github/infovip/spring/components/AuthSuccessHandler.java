package com.github.infovip.spring.components;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.configuration.DefaultWebAppConfiguration.BaseSessionInformation;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.log.BaseLogElement;
import com.github.infovip.core.log.LogManager;
import com.github.infovip.core.web.user.DefaultApplicationRole;
import com.github.infovip.core.web.user.UserSession;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.spring.config.ApplicationUser;

/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {


	@Autowired
	private LogManager<BaseLogElement<?>, WebApplicationContext, HttpServletRequest> logManager;
    
    @Autowired
    private UserServiceInterface<User> userService;

  	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        final ApplicationUser au = (ApplicationUser)authentication.getPrincipal();
        final User u = au.getUser();
        final UserSession userSession = new UserSession();
        
        userSession.setAuthenticated(true);
        userSession.setUserId(u.getUserId());
        userSession.setUserName(u.getUserName());
        userSession.setUserMail(u.getUserMail());
        userSession.setRole(DefaultApplicationRole.USER);
        userSession.setRegistrationDate(u.getLogRegistration().getCreationTime());
        userSession.setUser(u);
        
        au.setUserSession(userSession);
        
        request.getSession().setAttribute(BaseSessionInformation.USER_SESSION.toString(), userSession);
        request.getSession().setAttribute(BaseSessionInformation.AUTH_TIME.toString(), new Date(System.currentTimeMillis()));
        request.getSession().setAttribute(BaseSessionInformation.REMOTE_ADDR.toString(), request.getRemoteAddr());
        request.getSession().setAttribute(BaseSessionInformation.HEADER.toString(), request.getHeader("User-Agent"));
        u.setLastSeen(DefaultDateFormatter.timestamp());
        userService.save(u);
        
        response.sendRedirect("/activity");
	}

}
