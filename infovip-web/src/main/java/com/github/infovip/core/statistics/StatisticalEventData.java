package com.github.infovip.core.statistics;

import static com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration.STATISTICS_INDEX;
import static com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration.STATISTICS_TYPE_DATA;

import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;

/**
 * The {@link StatisticalEventData} represents the data that will be stored as event.
 * There may be different types of event that determines the information that the event occurs.
 * 
 * @author attila
 *
 */
public class StatisticalEventData<T extends DefaultStatisticalEvent<?>> implements ESDataElement<T> , GeneralEvent  {

	public String documentID;

	private ESOperationType defaultOperationType;

	private T data;
	
	public StatisticalEventData() {
		this.defaultOperationType = ESOperationType.INDEX;
	}
	
	public StatisticalEventData(T data) {
		this();
		this.data = data;
	}

	@Override
	public void setParent(String sessionId) {
		data.setSessionId(sessionId);
	}
	
	@Override
	public String id() {
		return documentID;
	}

	@Override
	public String index() {
		return STATISTICS_INDEX;
	}

	@Override
	public String type() {
		return STATISTICS_TYPE_DATA;
	}

	@Override
	public ESOperationType operation() {
		return defaultOperationType;
	}

	
	public void setDefaultOperationType(ESOperationType defaultOperationType) {
		this.defaultOperationType = defaultOperationType;
	}
	
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	
	@Override
	public T data() {
		return data;
	}

}
