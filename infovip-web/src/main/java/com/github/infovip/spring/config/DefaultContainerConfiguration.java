package com.github.infovip.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.infovip.spring.components.transaction.DefaultWebAppTransactionManager;

@Configuration
public class DefaultContainerConfiguration {

	
	@Bean
	public DefaultWebAppTransactionManager defaultWebAppTransactionManager() {
		return new DefaultWebAppTransactionManager();
	}
}
