package com.github.infovip.core.data;

import org.apache.log4j.Logger;

import com.github.infovip.core.elasticsearch.ESChildElement;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public class DefaultChildElement<T> implements ESChildElement<T>  {

	private String documentId;
	
	private String index;
	
	private String type;
	
	private String parent;
	
	private ESOperationType operation;
	
	private T data;
	
	private Logger logger = Logger.getLogger(DefaultDataElement.class);
	
	public DefaultChildElement(T data) {
		this.operation = ESOperationType.INDEX;
		this.data = data;
	}
	
	public DefaultChildElement(T data, ESOperationType operation) {
		this.operation = operation;
		this.data = data;
	}

	
	public DefaultChildElement(T data, ESOperationType operation, String documentId) {
		this.operation = operation;
		this.data = data;
	}
	
	@Override
	public String id() {
		if ( documentId == null && data instanceof BaseDataElement ) 
			documentId = ( (BaseDataElement) data ).getDocumentId();
		
		return documentId;
	}
	
	@Override
	public String parent() {
		return this.parent;
	}
	
	public DefaultChildElement<?> setParent(String parent) {
		this.parent = parent;
		return this;
	}
	
	public String getParent() {
		return parent;
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
	public ESOperationType operation() {
		return operation;
	}

	@Override
	public T data() {
		return data;
	}
	
	public DefaultChildElement<?> setDocumentId(String documentId) {
		this.documentId = documentId;
		return this;
	}
	
	public DefaultChildElement<?> setOperation(ESOperationType operation) {
		this.operation = operation;
		return this;
	}
	
	public DefaultChildElement<?> setIndex(String index) {
		this.index = index;
		return this;
	}
	
	public DefaultChildElement<?> setType(String type) {
		this.type = type;
		return this;
	}
	
	public void setData(T data) {
		this.data = data;
	}

	@Override
	public ESDataElement<T> operationIndex() {
		this.operation = ESOperationType.INDEX;
		return this;
	}

	@Override
	public ESDataElement<T> operationUpdate() {
		this.operation = ESOperationType.UPDATE;
		return this;
	}

	@Override
	public ESDataElement<T> operationDelete() {
		this.operation = ESOperationType.DELETE;
		return this;
	}
}
