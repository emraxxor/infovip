package com.github.infovip.spring.components.manager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
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
import com.google.gson.GsonBuilder;


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
		
		SearchRequestBuilder srb = template.getClient()
				.prepareSearch(metaData.getIndexName())
				.setTypes(metaData.getIndexType());

		if ( metaData.getRouting() != null ) 
			srb.setRouting(metaData.getRouting());

		
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		if ( metaData.exclusionStrategies().size() > 0 ) 
			metaData.exclusionStrategies().stream().forEach(o -> gsonBuilder.addDeserializationExclusionStrategy(o));
		
		Gson gson = gsonBuilder.create();
		
		
		SearchResponse response = srb
									.setQuery(query)
									.setExplain(false)
									.setSize(1)
									.execute()
									.actionGet(); 
		
		if ( response.getHits().getHits().length == 1 ) {
			T data = gson.fromJson(response.getHits().getHits()[0].getSourceAsString(), type);
			
			if ( data instanceof BaseDataElement )  {
				((BaseDataElement)data).setDocumentId(response.getHits().getHits()[0].getId());
				
				if ( metaData.getRouting() != null ) 
					((BaseDataElement)data).setRouting(metaData.getRouting());
			}
			
			return data;
		}
		
		return null;
	}


	public <T> T findDocumentByDocumentId(String id, IndexMetaData meta, Type type ) {
		GetRequestBuilder grb = esClient.prepareGet(meta.getIndexName(), meta.getIndexType(), id);
		
		if ( meta.getRouting() != null ) 
			grb.setRouting(meta.getRouting());
		
		
		GetResponse gr = grb.get();
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		if ( meta.exclusionStrategies().size() > 0 ) 
			meta.exclusionStrategies().stream().forEach(o -> gsonBuilder.addDeserializationExclusionStrategy(o));
		
		Gson gson = gsonBuilder.create();
		
		T o = gson.fromJson(gr.getSourceAsString(), type );
		
		if ( o instanceof BaseDataElement) 
			((BaseDataElement)o).setDocumentId(gr.getId());
		
		if ( meta.getRouting() != null ) 
			((BaseDataElement)o).setRouting(meta.getRouting());
	
		
		return o;
	}
	
	public <T> Long countByField(List<Field> fields, IndexMetaData metaData) {
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		
		for(Field field : fields ) 
			query.must(QueryBuilders.termQuery(field.getFieldName(), field.getFieldValue()));
		
		SearchRequestBuilder srb = template.getClient()
				.prepareSearch(metaData.getIndexName())
				.setTypes(metaData.getIndexType());
		
		if ( metaData.getRouting() != null ) 
			srb.setRouting(metaData.getRouting());
	
		SearchResponse response = srb
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
	
		SearchRequestBuilder srb = template.getClient()
				.prepareSearch(metaData.getIndexName())
				.setTypes(metaData.getIndexType());

		if ( metaData.getRouting() != null ) 
			srb.setRouting(metaData.getRouting());

		SearchResponse response = srb
									.setQuery(query)
									.setExplain(false)
									.setFrom(from)
									.setSize(size)
									.execute()
									.actionGet(); 
	
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		if ( metaData.exclusionStrategies().size() > 0 ) 
			metaData.exclusionStrategies().stream().forEach(o -> gsonBuilder.addDeserializationExclusionStrategy(o));
		
		Gson gson = gsonBuilder.create();
		
		if ( response.getHits().getHits().length > 0 ) {
			T data = gson.fromJson(response.getHits().getHits()[0].getSourceAsString(), type);
			
			if ( data instanceof BaseDataElement ) { 
				((BaseDataElement)data).setDocumentId(response.getHits().getHits()[0].getId());
				
				if ( metaData.getRouting() != null ) 
					((BaseDataElement)data).setRouting(metaData.getRouting());	
			}
			
			result.add(data);
		}
		
		return result;
	}
	
}
