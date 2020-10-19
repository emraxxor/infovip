package com.github.infovip.spring.config;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author Attila Barna
 * @doc https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/mvc.html
 *
 */

@Configuration
public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfiguration.class };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
	    registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}


}
