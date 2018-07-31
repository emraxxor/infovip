package com.github.infovip.spring.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultContextApplicationListener implements ApplicationListener<ApplicationEvent> {


	@Override
	public void onApplicationEvent(ApplicationEvent event) {
	}


}
