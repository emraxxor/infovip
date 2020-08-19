package com.github.infovip.data.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.data.UserPublicElement;
import com.github.infovip.core.data.UserPublicFormElement;
import com.github.infovip.core.scroll.AbstractScrollSource;
import com.github.infovip.entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Attila Barna
 *
 */
public class MemberWaterfallSource extends AbstractScrollSource<WebApplicationContext, UserPublicElement<?>> {

	private ElasticsearchRestTemplate template;

	private RestHighLevelClient client;

	private SearchResponse response;
	
	private BoolQueryBuilder query;
	
	List<UserPublicFormElement<User>> content;

	public MemberWaterfallSource(WebApplicationContext context, String token) {
		super(context, token);
		this.template = this.context.getBean(ElasticsearchRestTemplate.class);
		this.client = this.context.getBean(RestHighLevelClient.class);
		this.size = 50;
	}
	
	@Override
	public void query()  {
		try {
			if ( token == null || (token != null && token.equalsIgnoreCase("") ) )  {
				SearchRequest searchRequest = new SearchRequest(DefaultWebAppConfiguration.ESConfiguration.USERS_INDEX); 
				SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
				sourceBuilder.query(query);
				sourceBuilder.size(this.size);
				searchRequest.scroll(new TimeValue(30000L));
				searchRequest.source(sourceBuilder);			
				response = client.search(searchRequest, RequestOptions.DEFAULT);
			} else {
				SearchScrollRequest scrollRequest = new SearchScrollRequest(token);
				scrollRequest.scroll(new TimeValue(30000L));
				response = client.scroll(scrollRequest, RequestOptions.DEFAULT);
			}
			token = response.getScrollId();
			total = response.getHits().getTotalHits().value;
			count = response.getHits().getHits().length;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int count() {
		return this.count;
	}
	
	@Override
	public List<UserPublicElement<?>> content() {
		List<UserPublicElement<?>> result = new ArrayList<UserPublicElement<?>>();
		if (response.getHits().getHits().length > 0) {
				Arrays.asList(response.getHits().getHits())
					.stream()
					 .map(o ->  new Gson().fromJson(o.getSourceAsString(), new TypeToken<UserPublicElement<?>>(){}.getType()) )
					 .forEach( o -> result.add( (UserPublicElement<?>) o));
			
		} 
		return result;
	}

	@Override
	public void initializeQuery() {
		query = QueryBuilders.boolQuery().must( QueryBuilders.matchAllQuery() );
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeDestroy() {
		// TODO Auto-generated method stub
		
	}

	
}
