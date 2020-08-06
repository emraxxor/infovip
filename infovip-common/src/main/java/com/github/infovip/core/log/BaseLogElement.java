package com.github.infovip.core.log;

import com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration;
import com.github.infovip.core.data.DefaultDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public class BaseLogElement<T extends BaseLogDataElement> extends DefaultDataElement<T> {

	public BaseLogElement(T data, ESOperationType operation, String documentId) {
		super(data, operation, documentId);
		this.setIndex(ESConfiguration.LOGS_INDEX);
		this.setType(ESConfiguration.LOGS_TYPE);
	}
	
	public BaseLogElement(T data) {
		this(data, ESOperationType.INDEX);
	}
	
	public BaseLogElement(T data, ESOperationType type) {
		super(data,type);
		this.setIndex(ESConfiguration.LOGS_INDEX);
		this.setType(ESConfiguration.LOGS_TYPE);
	}

}
