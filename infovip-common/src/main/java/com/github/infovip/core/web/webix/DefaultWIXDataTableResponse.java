package com.github.infovip.core.web.webix;

import com.github.infovip.core.web.webix.markers.datatable.WIXDataSource;
import com.github.infovip.core.web.webix.markers.datatable.WIXDataTable;
import com.github.infovip.core.web.webix.markers.datatable.WIXQuery;

/**
 * 
 * @author Attila Barna
 *
 * @package infovip.web.webix

 *
 */
public class DefaultWIXDataTableResponse<DATA_TYPE, WEB_APP_CONTEXT> extends DatatableScrollResponse<DATA_TYPE, WEB_APP_CONTEXT>  {

	protected AbstractWIXDataSource<WEB_APP_CONTEXT, DATA_TYPE> source;
	
	private String token;
	
	
	public DefaultWIXDataTableResponse(WEB_APP_CONTEXT context) {
		this.context = context;
	}
	
	
	public DefaultWIXDataTableResponse(WEB_APP_CONTEXT context,int pos) {
		super(pos);
		this.context = context;
		
	}
	
	/**
	 * Typically specifies the query itself.
	 */
	@WIXQuery
	public void query() {
		source.query();
	}
	
	
	/**
	 * Specifies the data that can be parsed by the webix ui framework library
	 */
	@WIXDataTable
	public void datatable() {
		total_count =  source.total();
		pos = source.start();
		token = source.token();
		data.addAll( source.content() );	
	}
	
	
	/**
	 * Pre - init - set the source
	 * @param data
	 */
	@WIXDataSource
	public void source(AbstractWIXDataSource<WEB_APP_CONTEXT, DATA_TYPE> data) {
		this.source = data;
	}


	public String getToken() {
		return token;
	}

}
