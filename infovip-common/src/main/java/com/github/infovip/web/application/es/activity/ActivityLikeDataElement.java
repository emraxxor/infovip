package com.github.infovip.web.application.es.activity;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.data.DefaultDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public class ActivityLikeDataElement<T extends ActivityLikeElement> extends DefaultDataElement<T> {

	public ActivityLikeDataElement(T data, ESOperationType operation, String documentId) {
		super(data, operation, documentId);
		setIndex(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_INDEX);
		setType(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_TYPE);
	}

	public ActivityLikeDataElement(T data, ESOperationType operation) {
		super(data, operation);
		setIndex(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_INDEX);
		setType(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_TYPE);
	}

	public ActivityLikeDataElement(T data) {
		super(data);
		setIndex(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_INDEX);
		setType(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_LIKE_TYPE);
	}

	
}
