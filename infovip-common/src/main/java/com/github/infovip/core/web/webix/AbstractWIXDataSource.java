package com.github.infovip.core.web.webix;

import com.github.infovip.core.web.webix.markers.datatable.WIXDataSourceInterface;

/**
 * 
 * @author Attila Barna
 * 
 * @package infovip.web.webix
 *
 * @param <DATA_TYPE>
 */
public abstract class AbstractWIXDataSource<WEB_APP_CONTEXT, DATA_TYPE>  implements WIXDataSourceInterface<DATA_TYPE>  {

	
	protected String token;
	
	protected int start;
	
	protected int count;
	
	protected int size = 100;
	
	protected WEB_APP_CONTEXT context;
	
	public AbstractWIXDataSource(WEB_APP_CONTEXT context, String token) {
		this.token = token;
		this.context = context;
	}
	
	public AbstractWIXDataSource<WEB_APP_CONTEXT, DATA_TYPE> setStart(int start) {
		this.start = start;
		return this;
	}
	
	public AbstractWIXDataSource<WEB_APP_CONTEXT, DATA_TYPE> setCount(int count) {
		this.count = count;
		return this;
	}

	
	@Override
	public String token() {
		return token;
	}

	@Override
	public int start() {
		return this.start;
	}
	
	@Override
	public int count() {
		return this.count;
	}


	@Override
	public int size() {
		return size;
	}

	

}
