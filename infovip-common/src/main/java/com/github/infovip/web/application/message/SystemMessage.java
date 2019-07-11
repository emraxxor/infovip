package com.github.infovip.web.application.message;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.core.data.BaseDataElement;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;

public class SystemMessage<T extends BaseMessage> implements Message<T> {

	protected T data;
	
	protected ESOperationType operationType;
	
	public SystemMessage(T data, ESOperationType operationType) {
		this.data = data;
		this.operationType = operationType;
	}
	
	@Override
	public String id() {
		if  ( data instanceof BaseDataElement ) {
			if ( ((BaseDataElement) data).getDocumentId() != null ) {
				return ((BaseDataElement) data).getDocumentId();
			}
			return null;
		}
		return null;
	}

	@Override
	public String index() {
		return DefaultWebAppConfiguration.ESConfiguration.MESSAGE_INDEX;
	}

	@Override
	public String type() {
		return DefaultWebAppConfiguration.ESConfiguration.SYSTEM_MESSAGE_TYPE;
	}

	@Override
	public ESOperationType operation() {
		return this.operationType;
	}

	@Override
	public T data() {
		return this.data;
	}
	
	@Override
	public ESDataElement<T> operationIndex() {
		this.operationType = ESOperationType.INDEX;
		return this;
	}

	@Override
	public ESDataElement<T> operationUpdate() {
		this.operationType = ESOperationType.UPDATE;
		return this;
	}

	@Override
	public ESDataElement<T> operationDelete() {
		this.operationType = ESOperationType.DELETE;
		return this;
	}

}
