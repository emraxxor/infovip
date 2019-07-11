package com.github.infovip.core.statistics;

import com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;
import com.github.infovip.core.es.type.DefaultDocumentType;

/**
 * Represents a product item
 * @author attila
 *
 * @param <T>
 */
public class ESDocumentElement<T extends DefaultDocumentType> implements ESDataElement<T> {

	
	private T data;
	
	public ESDocumentElement(T data) {
		this.data = data;
	}

	public static <K extends DefaultDocumentType> ESDocumentElement<K> createDocument(K data) {
		return new ESDocumentElement<>(data);
	}
	
	@Override
	public String id() {
		return data.getDocumentID();
	}

	@Override
	public String index() {
		return ESConfiguration.INDEX;
	}

	@Override
	public String type() {
		return ESConfiguration.TYPE;
	}

	@Override
	public ESOperationType operation() {
		return ESOperationType.UPDATE;
	}

	@Override
	public T data() {
		return data;
	}

	@Override
	public ESDataElement<T> operationIndex() {
		
		return this;
	}

	@Override
	public ESDataElement<T> operationUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ESDataElement<T> operationDelete() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
