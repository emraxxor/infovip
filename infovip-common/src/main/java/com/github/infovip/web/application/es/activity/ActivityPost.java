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
public class ActivityPost<T extends ActivityElement> extends DefaultDataElement<T> {

	public ActivityPost(T data) {
		super(data);
		setIndex(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_POST_INDEX);
		setType(DefaultWebAppConfiguration.ESConfiguration.ACTIVITY_POST_TYPE);
		setOperation(ESOperationType.INDEX);
	}

	public ActivityPost(T data, ESOperationType operation, String documentId) {
		super(data, operation, documentId);
	}

	public ActivityPost(T data, ESOperationType operation) {
		super(data, operation);
	}

}
