package com.github.infovip.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.SecurityConfig;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.github.infovip.spring.DefaultViewResolver;
import com.github.infovip.spring.handler.AuthenticationInterceptor;
import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;

@EnableWebMvc
@Configuration
@ComponentScans( value =  { 
		@ComponentScan(value = "com.github.infovip.spring.controllers") , 
		@ComponentScan(value = "com.github.infovip.spring.components")
})
@Import({SecurityConfiguration.class})
public class WebMvcConfiguration implements WebMvcConfigurer {

	
	@Bean
	public InternalResourceViewResolver internalViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/web/internal/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setExposeContextBeansAsAttributes(true);
		viewResolver.setOrder(2);
		return viewResolver;

	}
	
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		viewResolver.setExposeContextBeansAsAttributes(true);
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
	@Bean
	public DefaultViewResolver viewResolver() {
		DefaultViewResolver dv = new DefaultViewResolver();
		dv.setJspResolver(internalViewResolver());
		dv.setTilesResolver(tilesViewResolver());
		return dv;
	}
	
	
	@Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/lib/**").addResourceLocations("/webjars");
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
        //registry.addResourceHandler("/modules/**").addResourceLocations("/WEB-INF/web/modules/");
        registry.addResourceHandler("/robots.txt").addResourceLocations("/WEB-INF/robots.txt");
    }

	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthenticationInterceptor())
					.addPathPatterns(
							"/admin",
							"/admin/**"
					)
					.excludePathPatterns( 
							"/admin/login"
					);
	}

	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tc = new TilesConfigurer();
		tc.setDefinitions(
				"/WEB-INF/tiles/defs/general.xml",
				"/WEB-INF/tiles/defs/widgets.xml",
				"/WEB-INF/tiles/defs/templates.xml"
		);
		return tc;
	}
}
