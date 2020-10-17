package com.github.infovip.model.gallery;

import static com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_PHOTO;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.core.scroll.AbstractBoolScrollSource;
import com.github.infovip.core.web.user.media.UserPhotoElement;

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
	public void initializeQuery() {
		query = QueryBuilders.boolQuery().must( QueryBuilders.matchAllQuery() );
	}

}
