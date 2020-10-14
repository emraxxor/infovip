package com.github.infovip.spring.components;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.core.log.BaseLogElement;
import com.github.infovip.core.log.LogManager;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;

/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class AuthenticationSuccessListener  implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private LogManager<BaseLogElement<?>, WebApplicationContext, HttpServletRequest> logManager;
    
    @Autowired
    private UserServiceInterface<User> userService;

    @Autowired
    private HttpServletRequest request;    

    

 
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
  
    }

}
