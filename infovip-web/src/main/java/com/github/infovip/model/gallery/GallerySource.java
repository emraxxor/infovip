package com.github.infovip.model.gallery;

import static com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_PHOTO;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.core.model.UserPhotoElement;
import com.github.infovip.core.scroll.AbstractBoolScrollSource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Attila Barna
 *
 */
public class GallerySource extends AbstractBoolScrollSource<UserPhotoElement>  {

	public GallerySource(WebApplicationContext context, String token) {
		super(context, token, USER_MEDIA_PHOTO);
	}

	@Override
	public void postInit() {
	
	}

	@Override
	public void beforeDestroy() {
		
	}
	
	@Override
	public UserPhotoElement convert(SearchHit o) {
		UserPhotoElement me = new Gson().fromJson(o.getSourceAsString(), new TypeToken<UserPhotoElement>(){}.getType()) ;
		me.setDocumentId(o.getId());
		return me;
	}

	@Override
	public void initializeQuery() {
		query = QueryBuilders.boolQuery().must( QueryBuilders.matchAllQuery() );
	}

}
