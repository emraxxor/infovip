package com.github.infovip.spring.components.transaction;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.github.infovip.core.data.type.TransactionType;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.es.type.DefaultDocumentType;
import com.github.infovip.web.application.business.BusinessContainerManager;
import com.github.infovip.web.application.transaction.TransactionValue;

@Component
public class DefaultWebAppTransactionManager {
	
	@Autowired
	private ElasticsearchTemplate template;

	private Client esClient;
	
	@Autowired
	private ESContainerInterface<ESDataElement<?>> esContainer;
	
	@Autowired
	private BusinessContainerManager<?> businessContainer;
	
	public DefaultWebAppTransactionManager() {
		
	}
	
	@PostConstruct
	public void postConstruct() {
		esClient = template.getClient();
	}
	
	
	public void transactionOnClickProduct(
				DefaultDocumentType ddt, 
				TransactionValue v, 
				TransactionType type, 
				HttpServletRequest request
		) {
		
		
	}

}
