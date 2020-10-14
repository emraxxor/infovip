package com.github.infovip.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.github.infovip.core.Container;
import com.github.infovip.core.web.CSSManager;
import com.github.infovip.core.web.JavascriptManager;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.user.CurrentUserInfo;
import com.github.infovip.spring.components.CurrentUserFacade;

@Configuration
@ComponentScans( value =  { 
		@ComponentScan("com.github.infovip.spring.config"),
		@ComponentScan("com.github.infovip.spring.services"),
		@ComponentScan("com.github.infovip.core.container"),
		@ComponentScan("com.github.infovip.core.statistics"),
		@ComponentScan("com.github.infovip.core.smtp"),
})
public class ApplicationConfiguration {


	@Bean(name="Configuration")
	public com.github.infovip.core.Configuration configuration() {
		return new com.github.infovip.core.Configuration();
	}
	
	
	@Bean(name = "container")
	@Scope("request")
	public Container container() {
		try {
			return new Container(new JavascriptManager(), new CSSManager());
		} catch (UnsupportedTypeException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
