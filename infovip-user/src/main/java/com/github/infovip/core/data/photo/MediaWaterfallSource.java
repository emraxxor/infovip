package com.github.infovip.core.data.photo;

import java.io.IOException;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.model.UserMediaElement;
import com.github.infovip.core.model.UserPhotoElement;
import com.github.infovip.core.scroll.AbstractBoolScrollSource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Attila Barna
 *
 */
public class MediaWaterfallSource extends AbstractBoolScrollSource<UserMediaElement>  {

	private Long userId;
	
	public MediaWaterfallSource(WebApplicationContext context, String token, Long uid) {
		super(context, token, DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_INDEX);
		this.userId = uid; 
	}
	

	@Override
	public void initializeQuery() {
		query = QueryBuilders.boolQuery().must( QueryBuilders.termQuery("userId", userId) );
	}

	private UserPhotoElement search(String id) {
		try {
			SearchRequest searchRequest = new SearchRequest(DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_PHOTO); 
			SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
			sourceBuilder.query(
						QueryBuilders
							.boolQuery()
							.must( QueryBuilders.termQuery("mediaId.keyword", id))
			);
			sourceBuilder.size(1);
			searchRequest.source(sourceBuilder);			
			response = client.search(searchRequest, RequestOptions.DEFAULT);
			if ( response.getHits().getTotalHits().value > 0L ) {
				UserPhotoElement ue = new Gson().fromJson(response.getHits().getAt(0).getSourceAsString(), new TypeToken<UserPhotoElement>(){}.getType()) ;
				ue.setDocumentId(response.getHits().getAt(0).getId());
				return ue;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	@Override
	public UserMediaElement convert(SearchHit o) {
		UserMediaElement me = new Gson().fromJson(o.getSourceAsString(), new TypeToken<UserMediaElement>(){}.getType()) ;
		me.setDocumentId(o.getId());
		me.setPhoto(search(o.getId()));
		return me;
	}
	
	@Override
	public void postInit() {
	}

	@Override
	public void beforeDestroy() {
	}

}
