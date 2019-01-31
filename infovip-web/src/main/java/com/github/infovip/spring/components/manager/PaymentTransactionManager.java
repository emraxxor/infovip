package com.github.infovip.spring.components.manager;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESDataElement;


/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class PaymentTransactionManager {

	
	@Autowired
	private ElasticsearchTemplate esTemplate;

	private Client esClient;
	
	@Autowired
	private ESContainerInterface<ESDataElement<?>> esContainer;

	
	public PaymentTransactionManager() {}
	
	
	@PostConstruct
	public void postConstruct() {
		esClient = esTemplate.getClient();
	}

}
