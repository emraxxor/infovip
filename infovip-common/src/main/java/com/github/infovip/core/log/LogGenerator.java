package com.github.infovip.core.log;

import java.util.function.Consumer;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 * @param <F>
 */
public interface LogGenerator<T extends BaseLogDataElement, F> {

	public T get();
	
	public LogGenerator<T, F> message(String msg);
	
	public LogGenerator<T, F> user(String user);
	
	public LogGenerator<T, F> component(String component);
	
	public LogGenerator<T, F> type(String type);
	
	public LogGenerator<T, F> put(String key, Object data);
	
	public LogGenerator<T, F> update( Consumer<T> c );
	
	public BaseLogElement<T> getLogElement();
}
