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
	
	public ESOperationType operation();
	
	public T data();
}
