package com.github.infovip.spring.components;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.core.log.BaseLogDataElement;
import com.github.infovip.core.log.BaseLogElement;
import com.github.infovip.core.log.LogManager;
import com.github.infovip.core.log.LogType;
import com.github.infovip.spring.config.ApplicationUser;
import com.github.infovip.spring.controllers.AuthenticationController;

/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class AuthenticationFailureListener  implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {


    @Autowired
    private HttpServletRequest request;    

	   
	@Autowired
	private LogManager<BaseLogElement<?>, WebApplicationContext, HttpServletRequest> logManager;

		
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		Object p = (ApplicationUser)event.getAuthentication().getPrincipal();
		logManager.synch(
				logManager
				.create( new BaseLogDataElement() , request)
				.user(p.toString())
				.type(LogType.AUTH)
				.component(AuthenticationController.class.getName())
				.message("Unsuccessful login into the system.")
				.getLogElement()
		);
	}

}
