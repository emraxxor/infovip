package com.github.infovip.core.elasticsearch;

/**
 * Specifies the required arguments of an element that will be inserted into the index.
 * @author attila
 *
 */
public interface ESExtendedDataElement<T> {

	public String id();
	
	public String index();
	
	public String type();

	default public String parentDocument() { return null; }
	
	default public String routing() { return null; }
	
	public ESOperationType operation();
	
	public T data();
	
	default public ESExtendedDataElement<T> operationIndex() { return null; }
	
	default public ESExtendedDataElement<T> operationUpdate() { return null; }
	
	default public ESExtendedDataElement<T> operationDelete() { return null; }
}
