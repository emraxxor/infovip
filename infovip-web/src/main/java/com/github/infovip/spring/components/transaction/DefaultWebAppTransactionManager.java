package com.github.infovip.spring.components.transaction;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;

import com.github.infovip.core.data.type.TransactionType;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.es.type.DefaultDocumentType;
import com.github.infovip.web.application.business.ClientContainerManager;
import com.github.infovip.web.application.transaction.TransactionValue;

@Component
public class DefaultWebAppTransactionManager {
	
	@Autowired
	private ElasticsearchRestTemplate template;

	private RestHighLevelClient esClient;
	
	@Autowired
	private ESContainerInterface<ESExtendedDataElement<?>> esContainer;
	
	@Autowired
	private ClientContainerManager<?> businessContainer;
	
	public DefaultWebAppTransactionManager() {
		
	}
	
	@PostConstruct
	public void postConstruct() {
	}
	
	
	public void transactionOnClickProduct(
				DefaultDocumentType ddt, 
				TransactionValue v, 
				TransactionType type, 
				HttpServletRequest request
		) {
		
		
	}

}
