package com.github.infovip.core.data.photo;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.scroll.AbstractBoolScrollSource;
import com.github.infovip.core.web.user.media.UserPhotoCommentElement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author attila
 *
 */
public class PhotoCommentSource extends AbstractBoolScrollSource<UserPhotoCommentElement>  {

	@Getter
	@Setter
	private String photoId;
	
	public PhotoCommentSource(WebApplicationContext context, String token, String photoId) {
		super(context, token, DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_PHOTO_COMMENT);
		this.photoId = photoId;
	}

	@Override
	public void initializeQuery() {
		query = QueryBuilders.boolQuery().must( QueryBuilders.termQuery("photoId.keyword", photoId) );
	}

	
	@Override
	public UserPhotoCommentElement convert(SearchHit o) {
		UserPhotoCommentElement me = new Gson().fromJson(o.getSourceAsString(), new TypeToken<UserPhotoCommentElement>(){}.getType()) ;
		me.setDocumentId(o.getId());
		return me;
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
