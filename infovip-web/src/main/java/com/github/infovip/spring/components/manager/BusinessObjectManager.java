package com.github.infovip.spring.components.manager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.github.infovip.core.data.BaseDataElement;
import com.github.infovip.core.data.Field;
import com.github.infovip.core.data.IndexMetaData;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.es.query.DocumentManager;
import com.github.infovip.web.application.business.BusinessContainerManager;
import com.google.gson.Gson;


/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class BusinessObjectManager implements BusinessContainerManager, DocumentManager {

	
	@Autowired
	private ElasticsearchTemplate template;

	private Client esClient;
	
	@Autowired
	private ESContainerInterface<ESDataElement<?>> esContainer;

	
	public BusinessObjectManager() {}
	
	
	@PostConstruct
	public void postConstruct() {
		esClient = template.getClient();
	}
	
	
	public <T> T findDocumentByField(List<Field> fields, IndexMetaData metaData, Type type) {
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		
		for(Field field : fields ) 
			query.must(QueryBuilders.termQuery(field.getFieldName(), field.getFieldValue()));
		
		SearchResponse response = template.getClient()
				.prepareSearch(metaData.getIndexName())
				.setTypes(metaData.getIndexType())
				.setQuery(query)
				.setExplain(false)
				.setSize(1)
				.execute()
				.actionGet(); 
		
		if ( response.getHits().getHits().length == 1 ) {
			T data = new Gson().fromJson(response.getHits().getHits()[0].getSourceAsString(), type);
			
			if ( data instanceof BaseDataElement ) 
				((BaseDataElement)data).setDocumentId(response.getHits().getHits()[0].getId());
			
			return data;
		}
		
		return null;
	}


	public <T> T findDocumentByDocumentId(String id, IndexMetaData meta, Type type ) {
		GetResponse gr = esClient.prepareGet(meta.getIndexName(), meta.getIndexType(), id).get();
		T o = new Gson().fromJson(gr.getSourceAsString(), type );
		
		if ( o instanceof BaseDataElement)
			((BaseDataElement)o).setDocumentId(gr.getId());
		
		return o;
	}
	
	public <T> Long countByField(List<Field> fields, IndexMetaData metaData) {
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		
		for(Field field : fields ) 
			query.must(QueryBuilders.termQuery(field.getFieldName(), field.getFieldValue()));
		
		SearchResponse response = template.getClient()
				.prepareSearch(metaData.getIndexName())
				.setTypes(metaData.getIndexType())
				.setQuery(query)
				.setExplain(false)
				.setSize(1)
				.execute()
				.actionGet(); 
		
		return response.getHits().getTotalHits();
	}
	
	public <T> List<T> findDocumentsByField(List<Field> fields, IndexMetaData metaData, Type type, int size, int from) {
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		List<T> result = new ArrayList<>();
		
		for(Field field : fields ) 
			query.must(QueryBuilders.termQuery(field.getFieldName(), field.getFieldValue()));
		
		SearchResponse response = template.getClient()
				.prepareSearch(metaData.getIndexName())
				.setTypes(metaData.getIndexType())
				.setQuery(query)
				.setExplain(false)
				.setFrom(from)
				.setSize(size)
				.execute()
				.actionGet(); 
		
		if ( response.getHits().getHits().length > 0 ) {
			T data = new Gson().fromJson(response.getHits().getHits()[0].getSourceAsString(), type);
			
			if ( data instanceof BaseDataElement ) 
				((BaseDataElement)data).setDocumentId(response.getHits().getHits()[0].getId());
			
			result.add(data);
		}
		
		return result;
	}
	
}
