package com.github.infovip.core.elasticsearch;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public interface ESChildElement<T> extends ESDataElement<T> {

	public String parent();
	
}
