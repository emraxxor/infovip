package com.github.infovip.spring.controllers.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.InnerHitBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.scroll.AbstractScrollSource;
import com.github.infovip.spring.config.ApplicationUser;
import com.github.infovip.web.application.es.activity.ActivityCommentElement;
import com.github.infovip.web.application.es.activity.ActivityJoinType;
import com.github.infovip.web.application.es.activity.ActivityLikeElement;
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

	private ElasticsearchRestTemplate template;

	private RestHighLevelClient client;

	private SearchResponse response;
	
	private BoolQueryBuilder query;
	
	List<ActivityPostElement> content;
	
	private final ApplicationUser appUser;
	
	public ActivitySource(WebApplicationContext context, String token, ApplicationUser appUser) {
		super(context, token);
		this.template = this.context.getBean(ElasticsearchRestTemplate.class);
		this.client = this.context.getBean(RestHighLevelClient.class);
		this.appUser = appUser;
		this.size = 10;
	}

	@Override
	public void query()  {
		try {
			if ( token == null || (token != null && token.equalsIgnoreCase("") ) )  {
				SearchRequest searchRequest = new SearchRequest(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_POST_INDEX, DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_INDEX ); 
				SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
				sourceBuilder.query(query);
				sourceBuilder.sort("date", SortOrder.DESC);
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
	
	public static ExclusionStrategy DefaultActivityExclusionStrategy() {
		return new ExclusionStrategy() {
			
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
		};
	}
	
	@Override
	public List<ActivityResponseElement> content() {
		Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(DefaultActivityExclusionStrategy()).create();

		if (response.getHits().getHits().length > 0) {
			  return Arrays.asList(response.getHits().getHits()).stream().map(o -> {
				  
				  ActivityResponseElement e = gson.fromJson(o.getSourceAsString(), new TypeToken<ActivityResponseElement>(){}.getType()  );
				  for( SearchHit hit : o.getInnerHits().get("comment").getHits() )  {
					  ActivityCommentElement comment = gson.fromJson( hit.getSourceAsString() ,  new TypeToken<ActivityCommentElement>(){}.getType()  ) ;
					  comment.setDocumentId(hit.getId());
					  comment.setRouting(e.getDocumentId());
					  
					  for ( SearchHit sh : hit.getInnerHits().get("like-comment").getHits() ) {
						  ActivityLikeElement ale = gson.fromJson(sh.getSourceAsString(), new TypeToken<ActivityLikeElement>(){}.getType()  );
						  ale.setDocumentId(hit.getId());
						  ale.setRouting(o.getId());
						  comment.addLike(ale);
					  }
					  
					  for ( SearchHit sh : hit.getInnerHits().get("reply").getHits() ) {
						  ActivityCommentElement ace = gson.fromJson(sh.getSourceAsString(), new TypeToken<ActivityCommentElement>(){}.getType()  );
						  ace.setDocumentId(hit.getId());
						  ace.setRouting(o.getId());
						  comment.addReply(ace);
					  }
					  
					  e.addCommentElement(  comment );
					  comment.setTotalLikeCount( hit.getInnerHits().get("like-comment").getTotalHits().value  );
				  }
				  
				  for ( SearchHit sh : o.getInnerHits().get("like-post").getHits() ) {
					  ActivityLikeElement ale = gson.fromJson(sh.getSourceAsString(), new TypeToken<ActivityLikeElement>(){}.getType()  );
					  ale.setDocumentId(sh.getId());
					  ale.setRouting(o.getId());
					  e.addLike(ale);
				  }
				 
				  e.setRouting(o.getId());
				  e.setTotalLikeCount( o.getInnerHits().get("like-post").getTotalHits().value );
				  e.setDocumentId(o.getId());
				  return e;
			  }).collect(Collectors.toList()) ;
			
		} else {
			return new ArrayList<ActivityResponseElement>();
		}

	}
	
	@Override
	public <T1, T2> void onProcess(T1 data, T2 element) {
		super.onProcess(data, element);
	}

	@Override
	public void initializeQuery() {
		query = QueryBuilders.boolQuery()
				.must( QueryBuilders.termQuery("uid", appUser.getUser().getUserId()) )
				.must( QueryBuilders.termQuery("join", ActivityJoinType.POST.value() ) )
				.should( 
						JoinQueryBuilders
						.hasChildQuery("comment", 
									QueryBuilders
									.boolQuery().must(QueryBuilders.matchAllQuery())
									.should(
									  JoinQueryBuilders.hasChildQuery("like-comment", QueryBuilders.matchAllQuery(), ScoreMode.None)	
									  .innerHit(new InnerHitBuilder().setSize(50))
									 )
									.should(
											  JoinQueryBuilders.hasChildQuery("reply", QueryBuilders.matchAllQuery(), ScoreMode.None)	
											  .innerHit(new InnerHitBuilder().setSize(50))
									 )
						, ScoreMode.None)
						.innerHit(new InnerHitBuilder().setSize(50)) 		
				)
				.should( 
						JoinQueryBuilders
						.hasChildQuery("like-post", QueryBuilders.matchAllQuery() , ScoreMode.None)
						.innerHit(new InnerHitBuilder().setSize(50))		
				);
	}

	@Override
	public void postInit() {
		
	}

	@Override
	public void beforeDestroy() {
	
	}
	
}
