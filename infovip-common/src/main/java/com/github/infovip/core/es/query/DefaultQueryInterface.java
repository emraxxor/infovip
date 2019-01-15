package com.github.infovip.core.es.query;

import java.util.List;

/**
 * 
 * @author Attila Barna
 *
 */
public interface DefaultQueryInterface<WEB_APP_CONTEXT, DB_TEMPLATE, DATA_ELEMENT> {

	
	/**
	 * In general, the search is implemented here.
	*
	 */
	public void query();
	
	
	/**
	 * It defines the template which provides the connection to the database
	 * @return
	 */
	public DB_TEMPLATE template();
	
	/**
	 * The context of the web application from which all items in the container are available.
	 * @return
	 */
	public WEB_APP_CONTEXT context();

	/**
	 * The final content that the user receives.
	 * @return
	 */
	public List<DATA_ELEMENT> content();
}
