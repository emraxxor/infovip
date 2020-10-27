package com.github.infovip.spring.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import com.github.infovip.core.DefaultElasticsearchConfiguration;
import com.github.infovip.core.elasticsearch.DefaultElasticsearchTemplate;
import com.github.infovip.core.elasticsearch.ESConnection;

@Configuration
@ComponentScans(value = {
		@ComponentScan(basePackages = "com.github.infovip.core.model")
})
public class ElasticsearchConfiguration  extends  AbstractElasticsearchConfiguration { 

	
    @Override
    @Bean(name = "elasticsearchClient")
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()  
            .connectedTo("es:9200")
            .build();

        return RestClients.create(clientConfiguration).rest();                         
    }
    
   /**
    @Bean(name = "elasticsearchRestTemplate")
    @Primary
    public ElasticsearchRestTemplate elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    } **/
    

    @Bean(name = "elasticsearchRestTemplate")
    @Primary
    public DefaultElasticsearchTemplate deafultElasticsearchTemplate() {
    	return new DefaultElasticsearchTemplate(elasticsearchClient());
    }


    @Bean(name = "defaultElasticsearchConfiguration" )
    public DefaultElasticsearchConfiguration defaultElasticsearchConfiguration() {
    	return new DefaultElasticsearchConfiguration(deafultElasticsearchTemplate(), elasticsearchClient());
    }
    
    @Bean(name = "esConnection")
    public ESConnection esConnection() {
    	return new ESConnection(defaultElasticsearchConfiguration());
    }
    
	  //@Bean
	  //@Override
	  /**
	   * Removal of the used Jackson Mapper
	   * One of the changes in version 4.0.x is that Spring Data Elasticsearch does not use the Jackson Mapper anymore to map an entity to the JSON representation needed for Elasticsearch (see Elasticsearch Object Mapping). In version 3.2.x the Jackson Mapper was the default that was used. It was possible to switch to the meta-model based converter (named ElasticsearchEntityMapper) by explicitly configuring it (Meta Model Object Mapping).
	   *
	   * In version 4.0.x the meta-model based converter is the only one that is available and does not need to be configured explicitly. If you had a custom configuration to enable the meta-model converter by providing a bean like this:
	   *
	   *
	   * @return
	   */
	  /*public ElasticsearchEntityMapper entityMapper() {
		ElasticsearchEntityMapper entityMapper = new DefaultElasticsearchEntityMapper( elasticsearchMappingContext(), new DefaultConversionService());
	    entityMapper.setConversions(elasticsearchCustomConversions());  
	  	return entityMapper;
	  }*/
    
      
}
