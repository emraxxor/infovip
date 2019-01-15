package com.github.infovip.core.es.query;

import com.github.infovip.core.es.query.type.Query;
import com.github.infovip.core.es.query.type.QuerySource;
import com.github.infovip.core.es.query.type.ResponseData;

/**
 * 
 * @author Attila Barna
 *
 * @param <QUERY>
 */
public class DefaultQueryResponse<QUERY extends DefaultQueryInterface<?, ?, ?>> {
	
	protected QUERY querySource;
	
	private Object data;
	
	@Query
	public void query() {
		querySource.query();
	}
	
	@QuerySource
	public void init(QUERY query) {
		this.querySource = query;
	}
	
	@ResponseData
	public void response() {
		this.data = this.querySource.content(); 
	}
	
	public Object getData() {
		return data;
	}
	
}
