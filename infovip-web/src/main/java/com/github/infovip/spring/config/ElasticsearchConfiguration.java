package com.github.infovip.spring.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.EntityMapper;

import com.github.infovip.core.elasticsearch.DefaultElasticsearchEntityMapper;
import com.github.infovip.core.elasticsearch.DefaultObjectMapper;

@Configuration
public class ElasticsearchConfiguration /** extends  AbstractElasticsearchConfiguration **/ { 

	/**
	  @Bean
	  @Override
	  public RestHighLevelClient elasticsearchClient() {
	        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()  
	                .connectedTo("localhost:9200")
	                .build();

	        return RestClients.create(clientConfiguration).rest();                         
	  }

	  @Bean
	  @Override
	  public ElasticsearchEntityMapper entityMapper() {
		ElasticsearchEntityMapper entityMapper = new DefaultElasticsearchEntityMapper( elasticsearchMappingContext(), new DefaultConversionService());
	    entityMapper.setConversions(elasticsearchCustomConversions());  
	  	return entityMapper;
	  }
	  
	  **/
	  
}
