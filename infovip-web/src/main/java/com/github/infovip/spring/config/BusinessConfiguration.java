package com.github.infovip.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.infovip.spring.components.manager.DefaultDocumentManager;
import com.github.infovip.spring.components.manager.PaymentTransactionManager;
import com.github.infovip.spring.components.manager.WebshopComponent;

/**
 * 
 * @author Attila Barna
 *
 */
@Configuration
public class BusinessConfiguration {
	
	@Bean
	public WebshopComponent webshopComponent() {
		return new WebshopComponent();
	}
	
	@Bean
	public PaymentTransactionManager paymentTransactionManager() {
		return new PaymentTransactionManager();
	}
	
	@Bean
	public DefaultDocumentManager businessObjectManager() {
		return new DefaultDocumentManager();
	}
}
