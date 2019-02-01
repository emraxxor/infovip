package com.github.infovip.core.data;

import org.apache.log4j.Logger;

import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;

public class DefaultDataElement<T> implements ESDataElement<T> {

	private String documentId;
	
	private String index;
	
	private String type;
	
	private String routing;
	
	private ESOperationType operation;
	
	private T data;
	
	private Logger logger = Logger.getLogger(DefaultDataElement.class);
	
	public DefaultDataElement(T data) {
		this.operation = ESOperationType.INDEX;
		this.data = data;
		this.routing = null;
	}
	
	public DefaultDataElement(T data, ESOperationType operation) {
		this.operation = operation;
		this.data = data;
		this.routing = null;
	}

	
	public DefaultDataElement(T data, ESOperationType operation, String documentId) {
		this.operation = operation;
		this.data = data;
		this.routing = null;
	}
	
	@Override
	public String id() {
		if ( documentId == null && data instanceof BaseDataElement ) 
			documentId = ( (BaseDataElement) data ).getDocumentId();
		
		return documentId;
	}

	@Override
	public String index() {
		return index;
	}

	@Override
	public String type() {
		return type;
	}

	@Override
	public String routing() {
		return this.routing;
	}
	
	@Override
	public ESOperationType operation() {
		return operation;
	}

	@Override
	public T data() {
		return data;
	}
	
	public DefaultDataElement<?> setDocumentId(String documentId) {
		this.documentId = documentId;
		return this;
	}
	
	public DefaultDataElement<?> setOperation(ESOperationType operation) {
		this.operation = operation;
		return this;
	}
	
	public DefaultDataElement<?> setIndex(String index) {
		this.index = index;
		return this;
	}
	
	public DefaultDataElement<?> setType(String type) {
		this.type = type;
		return this;
	}
	
	public DefaultDataElement<T> setRouting(String routing) {
		this.routing = routing;
		return this;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}


