package com.github.infovip.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.github.infovip.core.web.application.ApplicationConfiguration;

/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class DefaultContextApplicationListener implements ApplicationListener<ApplicationEvent> {

	@Autowired
	private ApplicationConfiguration applicationConfiguration;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
			
	}


}
