package com.github.infovip.core.es.query;

import java.util.List;

/**
 * 
 * @author Attila Barna
 *
 * @param <WEB_APP_CONTEXT>
 * Web application context type, usually org.springframework.web.context.WebApplicationContext
 * 
 * @param <DB_TEMPLATE>
 * The template that defines the template for the database
 * 
 */
public abstract class DefaultAbstractQuery<WEB_APP_CONTEXT, DB_TEMPLATE, DATA_ELEMENT> implements DefaultQueryInterface<WEB_APP_CONTEXT, DB_TEMPLATE, DATA_ELEMENT> {

	protected WEB_APP_CONTEXT context;
	
	protected DB_TEMPLATE template;
	
	
	public DefaultAbstractQuery(WEB_APP_CONTEXT context) {
		this.context = context;
	}
	
	@Override
	public DB_TEMPLATE template() {
		return template;
	}
	
	@Override
	public WEB_APP_CONTEXT context() {
		return context;
	}
	
	
	/**
	 * Usually 
	 * @return
	 */
	public abstract List<DATA_ELEMENT> content();
	
}
