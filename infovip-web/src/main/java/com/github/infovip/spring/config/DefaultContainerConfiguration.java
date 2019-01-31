package com.github.infovip.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.infovip.core.web.application.ApplicationConfiguration;
import com.github.infovip.spring.components.core.ModulePath;
import com.github.infovip.spring.components.message.DefaultMessageManager;
import com.github.infovip.spring.components.thread.DefaultThreadManager;
import com.github.infovip.spring.components.transaction.DefaultWebAppTransactionManager;

@Configuration
public class DefaultContainerConfiguration {

	@Bean
	public DefaultWebAppTransactionManager defaultWebAppTransactionManager() {
		return new DefaultWebAppTransactionManager();
	}
	
	@Bean
	public ApplicationConfiguration defaultApplicationConfiguration() {
		return new ApplicationConfiguration();
	}
	
	@Bean
	public DefaultThreadManager defaultThreadManager() {
		return new DefaultThreadManager();
	}
	
	@Bean
	public DefaultMessageManager defaultMessageManager() {
		return new DefaultMessageManager();
	}
	
	@Bean(name={"ModulePath"})
	public ModulePath defaultModulePath() {
		return new ModulePath();
	}
}
