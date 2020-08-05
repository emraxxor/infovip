package com.github.infovip.spring.components.manager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.infovip.core.data.BaseDataElement;
import com.github.infovip.core.data.Field;
import com.github.infovip.core.data.IndexMetaData;
import com.github.infovip.core.elasticsearch.ESConnection;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.es.query.DocumentManager;
import com.github.infovip.web.application.business.BusinessContainerManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;


/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class BusinessObjectManager implements BusinessContainerManager, DocumentManager {

	
	@Autowired
	private RestHighLevelClient restHighLevelClient;

	@Autowired
	private ESContainerInterface<ESDataElement<?>> esContainer;
	
	@Autowired
	private ESConnection esConnection;
	
	
	public BusinessObjectManager() {}
	
	
	@PostConstruct
	public void postConstruct() {}
	
	
	public <T> T findDocumentByField(List<Field> fields, IndexMetaData metaData, Type type) {
		try {
			BoolQueryBuilder query = QueryBuilders.boolQuery();
			
			for(Field field : fields ) 
				query.must(QueryBuilders.termQuery(field.getFieldName(), field.getFieldValue()));

			
			SearchRequest sr = new SearchRequest(metaData.getIndexName());
			SearchSourceBuilder src = new SearchSourceBuilder();
			src.query(query);
			src.size(1);
			src.explain(false);

			if ( metaData.getRouting() != null ) 
				sr.routing(metaData.getRouting());

			SearchResponse response = restHighLevelClient.search(sr, RequestOptions.DEFAULT);
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			if ( metaData.exclusionStrategies().size() > 0 ) 
				metaData.exclusionStrategies().stream().forEach(o -> gsonBuilder.addDeserializationExclusionStrategy(o));
			
			Gson gson = gsonBuilder.create();
			
			if ( response.getHits().getHits().length == 1 ) {
				T data = gson.fromJson(response.getHits().getHits()[0].getSourceAsString(), type);
				
				if ( data instanceof BaseDataElement )  {
					((BaseDataElement)data).setDocumentId(response.getHits().getHits()[0].getId());
					
					if ( metaData.getRouting() != null ) 
						((BaseDataElement)data).setRouting(metaData.getRouting());
				}
				
				return data;
			}
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}


	public <T> T findDocumentByDocumentId(String id, IndexMetaData meta, Type type ) {
		
		try {
			GetRequest grq = new GetRequest(meta.getIndexName());
			grq.fetchSourceContext(FetchSourceContext.FETCH_SOURCE);
			
			if ( meta.getRouting() != null ) 
				grq.routing(meta.getRouting());
			
			
			GetResponse gr = restHighLevelClient.get(grq, RequestOptions.DEFAULT) ;
			
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
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public <T> Long countByField(List<Field> fields, IndexMetaData metaData) {
		try {
			BoolQueryBuilder query = QueryBuilders.boolQuery();
			
			for(Field field : fields ) 
				query.must(QueryBuilders.termQuery(field.getFieldName(), field.getFieldValue()));
			
			SearchRequest rq = new SearchRequest(metaData.getIndexName());
			SearchSourceBuilder ssb = new SearchSourceBuilder();
			ssb.query(query);
			ssb.size(1);

			rq.source(ssb);
			
			if ( metaData.getRouting() != null ) 
				rq.routing(metaData.getRouting());

			SearchResponse response = restHighLevelClient.search(rq, RequestOptions.DEFAULT);
			
			return response.getHits().getTotalHits().value;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public <T> List<T> findDocumentsByField(List<Field> fields, IndexMetaData metaData, Type type, int size, int from) {
		final BoolQueryBuilder query = QueryBuilders.boolQuery();
		final List<T> result = new ArrayList<>();
		final SearchRequest rq = new SearchRequest(metaData.getIndexName());
		final SearchSourceBuilder ssb = new SearchSourceBuilder();

		for(Field field : fields ) 
			query.must(QueryBuilders.termQuery(field.getFieldName(), field.getFieldValue()));
	
		ssb.query(query);
		ssb.size(size);

		if ( metaData.getRouting() != null ) 
			rq.routing(metaData.getRouting());

		try {
			SearchResponse response = restHighLevelClient.search(rq, RequestOptions.DEFAULT);
				
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
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
