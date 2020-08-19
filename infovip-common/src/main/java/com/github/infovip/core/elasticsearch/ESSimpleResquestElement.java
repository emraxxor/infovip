package com.github.infovip.core.elasticsearch;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.ExclusionStrategy;


/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public interface ESSimpleResquestElement<TDATAELEMENT, T extends ESExtendedDataElement<TDATAELEMENT>> {
	
	public String index();
	
	public String type();
	
	public String id();
	
	public String routing();
	
	public void setRouting(String r);
	
	public T elem();
	
	public TDATAELEMENT data();
	
	public void setResponse( TDATAELEMENT data);
	
	public Type dataType();
	
	public List<ExclusionStrategy> exclusionStrategies();
	
}
