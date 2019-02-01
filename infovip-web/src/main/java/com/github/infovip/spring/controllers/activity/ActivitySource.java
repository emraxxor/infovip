package com.github.infovip.spring.controllers.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.InnerHitBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.scroll.AbstractScrollSource;
import com.github.infovip.web.application.es.activity.ActivityCommentElement;
import com.github.infovip.web.application.es.activity.ActivityJoinType;
import com.github.infovip.web.application.es.activity.ActivityPost;
import com.github.infovip.web.application.es.activity.ActivityPostElement;
import com.github.infovip.web.application.es.activity.ActivityResponseElement;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


/**
 * 
 * @author Attila Barna
 *
 */
public class ActivitySource extends AbstractScrollSource<WebApplicationContext, ActivityResponseElement> {

	private ElasticsearchTemplate template;

	private Client client;

	private SearchResponse response;
	
	private BoolQueryBuilder query;
	
	List<ActivityPostElement> content;
	
	public ActivitySource(WebApplicationContext context, String token) {
		super(context, token);
		this.template = this.context.getBean(ElasticsearchTemplate.class);
		this.client = this.template.getClient();
	}

	@Override
	public void query() {
		if ( token == null || (token != null && token.equalsIgnoreCase("") ) )  {
			response = client
						.prepareSearch(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_POST_INDEX)
						.setTypes(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_POST_TYPE)
						.setScroll(new TimeValue(30000L))
						.setQuery(query)
						.addSort("date",  SortOrder.DESC )
						.setExplain(true)
						.setSize(this.size)
						.execute()
						.actionGet();
		} else {
			response = client.prepareSearchScroll(token).setScroll(new TimeValue(60000)).execute().actionGet();
		}
		token = response.getScrollId();
		total = response.getHits().getTotalHits();
		count = response.getHits().getHits().length;
	}

	@Override
	public int count() {
		return this.count;
	}
	
	@Override
	public List<ActivityResponseElement> content() {
		Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(new ExclusionStrategy() {
			
			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				if ( f.getName().equalsIgnoreCase("join") )
					return true;
				
				return false;
			}
			
			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		}).create();

		if (response.getHits().getHits().length > 0) {
			  return Arrays.asList(response.getHits().getHits()).stream().map(o-> {
				  ActivityResponseElement e = gson.fromJson(o.getSourceAsString(), new TypeToken<ActivityResponseElement>(){}.getType()  );
				  for( SearchHit hit : o.getInnerHits().get("comment").getHits() )  {
					  ActivityCommentElement comment = gson.fromJson( hit.getSourceAsString() ,  new TypeToken<ActivityCommentElement>(){}.getType()  ) ;
					  comment.setDocumentId(hit.getId());
					  e.addCommentElement(  comment );
				  }
				  e.setDocumentId(o.getId());
				  return e;
			  }).collect(Collectors.toList()) ;
			
		} else {
			return new ArrayList<ActivityResponseElement>();
		}

	}

	@Override
	public void initializeQuery() {
		query = QueryBuilders.boolQuery()
				.must( QueryBuilders.termQuery("join", ActivityJoinType.POST.value() ) )
				.should( 
						JoinQueryBuilders.hasChildQuery("comment", QueryBuilders.matchAllQuery() , ScoreMode.None)
						.innerHit(new InnerHitBuilder().setSize(10)) 
				);
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
