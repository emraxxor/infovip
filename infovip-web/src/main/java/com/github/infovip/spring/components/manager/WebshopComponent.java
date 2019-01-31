package com.github.infovip.spring.components.manager;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;


/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class WebshopComponent {

	@Autowired
	private ElasticsearchTemplate esTemplate;

	private Client esClient;
	
	public WebshopComponent() {
	}
	
	
	@PostConstruct
	public void postConstruct() {
		esClient = esTemplate.getClient();
	}

}
