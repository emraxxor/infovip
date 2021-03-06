package com.github.infovip.core.data.photo;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.model.UserPhotoElement;
import com.github.infovip.core.scroll.AbstractBoolScrollSource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PhotoWaterfallSource extends AbstractBoolScrollSource<UserPhotoElement>  {

	private Long userId;
	
	private String mediaId;
	
	public PhotoWaterfallSource(WebApplicationContext context, String token, Long uid) {
		super(context, token, DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_PHOTO);
		this.userId = uid; 
	}
	

	@Override
	public void initializeQuery() {
		query = QueryBuilders.boolQuery().must( QueryBuilders.termQuery("userId", userId) );
		
		if ( mediaId != null ) 
			query.must( QueryBuilders.termQuery("mediaId.keyword", mediaId) );
		
	}

	
	@Override
	public UserPhotoElement convert(SearchHit o) {
		UserPhotoElement me = new Gson().fromJson(o.getSourceAsString(), new TypeToken<UserPhotoElement>(){}.getType()) ;
		me.setDocumentId(o.getId());
		return me;
	}
	
	public PhotoWaterfallSource setMediaId(String mediaId) {
		this.mediaId = mediaId;
		return this;
	}
	
	@Override
	public void postInit() {
	}

	@Override
	public void beforeDestroy() {
	}

}
