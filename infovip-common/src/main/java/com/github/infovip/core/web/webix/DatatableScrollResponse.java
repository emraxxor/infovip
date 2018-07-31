package com.github.infovip.core.web.webix;


/**
 * 
 * @author Attila Barna
 * 
 * @package infovip.web.webix
 *
 * WEB_CONTEXT specifies the current WebApplicationContext
 */
public class DatatableScrollResponse<T, WEB_CONTEXT> extends DataTableResponse<T> {

	
	protected WEB_CONTEXT context; 
	
	public DatatableScrollResponse() {
		super();
	}
	
	public DatatableScrollResponse(int pos) {
		super(pos);
	}

}
