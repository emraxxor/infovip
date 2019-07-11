package com.github.infovip.core.data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;
import com.github.infovip.core.es.type.IgnoreField;

public class DefaultDataElement<T> implements ESDataElement<T> {

	private String documentId;
	
	private String index;
	
	private String type;
	
	private String routing;
	
	private ESOperationType operation;
	
	private String parentDocument;
	
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
	public String parentDocument() {
		return this.parentDocument;
	}
	
	public String getParentDocument() {
		return parentDocument;
	}
	
	public void setParentDocument(String parentDocument) {
		this.parentDocument = parentDocument;
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
	
	
	public <TELEM extends T> void convertDataElement(Class<TELEM> dataClass) {
		this.data = toDataElem(dataClass);
	}
	
	public <TELEM extends T> TELEM toDataElem(Class<TELEM> dataClass) {
		return toDataElem(dataClass, data.getClass().getDeclaredFields());
	}
	
	public <TELEM extends T> TELEM toDataElem(Class<TELEM> dataClass, Field[] fields) {
		try {
			TELEM object = dataClass.newInstance();
			
			for(Field f : fields ) {
				if ( f.getAnnotation(IgnoreField.class)  != null ) continue;
				Method s = object.getClass().getMethod("set" + StringUtils.capitalize(f.getName()) , f.getType());
				s.invoke(object,  this.data().getClass().getMethod("get" + StringUtils.capitalize(f.getName())).invoke(data) );
			}
			
			if ( data instanceof BaseDataElement && object instanceof BaseDataElement ) {
				((BaseDataElement)object).setDocumentId( ((BaseDataElement) data ).getDocumentId() );
			}
			
			return object;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			logger.error(e);
		} catch (InstantiationException e) {
			logger.error(e);
		}
		return null;		

	}


}


