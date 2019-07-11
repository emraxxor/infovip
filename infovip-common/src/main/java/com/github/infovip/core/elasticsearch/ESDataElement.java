package com.github.infovip.core.elasticsearch;

/**
 * Specifies the required arguments of an element that will be inserted into the index.
 * @author attila
 *
 */
public interface ESDataElement<T> {

	public String id();
	
	public String index();
	
	public String type();

	default public String parentDocument() { return null; }
	
	default public String routing() { return null; }
	
	public ESOperationType operation();
	
	public T data();
	
	default public ESDataElement<T> operationIndex() { return null; }
	
	default public ESDataElement<T> operationUpdate() { return null; }
	
	default public ESDataElement<T> operationDelete() { return null; }
}
