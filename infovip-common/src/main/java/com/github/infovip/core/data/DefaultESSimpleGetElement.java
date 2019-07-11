package com.github.infovip.core.data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.github.infovip.core.elasticsearch.ESSimpleResquestElement;
import com.google.gson.ExclusionStrategy;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public class DefaultESSimpleGetElement<TDATAELEMENT, T extends DefaultDataElement<TDATAELEMENT>> implements ESSimpleResquestElement<TDATAELEMENT, T> {

	private T elem;
	
	private String id;
	
	private String type;
	
	private String index;
	
	private Type dataType;
	
	private List<ExclusionStrategy> strategies;
	
	private String routing;
	
	public DefaultESSimpleGetElement(T elem, Type t) {
		this.elem = elem;
		this.index = elem.index();
		this.type = elem.type();
		this.id = elem.id();
		this.dataType = t;
		this.strategies = new ArrayList<>();
	}
	
	public DefaultESSimpleGetElement(T elem, Type t, List<ExclusionStrategy> strategies) {
		this.elem = elem;
		this.index = elem.index();
		this.type = elem.type();
		this.id = elem.id();
		this.dataType = t;
		this.strategies = strategies;
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
	public String id() {
		return id;
	}

	@Override
	public void setResponse( TDATAELEMENT data) {
		this.elem.setData( data );
	}
	
	@Override
	public Type dataType() {
		return this.dataType;
	}

	@Override
	public TDATAELEMENT data() {
		return this.elem.data();
	}
	
	@Override
	public List<ExclusionStrategy> exclusionStrategies() {
		return strategies;
	}
	
	@Override
	public String routing() {
		return routing;
	}
	
	@Override
	public void setRouting(String r) {
		this.routing = r;
	}
	
	@Override
	public T elem() {
		return elem;
	}
}
