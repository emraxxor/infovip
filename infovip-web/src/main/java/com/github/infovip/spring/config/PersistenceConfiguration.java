package com.github.infovip.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.github.infovip.spring.repositories")
@EnableElasticsearchRepositories(basePackages = "com.github.infovip.spring.elasticsearch.repositories")
@ComponentScans(value = {
		@ComponentScan(basePackages = "com.github.infovip.spring.elasticsearch.repositories")
})
public class PersistenceConfiguration {

	@Bean
	public ElasticsearchConfigurationSupport support() {
		return new ElasticsearchConfigurationSupport();
	}
	
}
