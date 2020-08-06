package com.github.infovip.core.log;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public interface LogManager<T extends BaseLogElement<?>, CONTEXT, REQUEST>  {

	public void addLog(T log);
	
	public Object synch(T log);
	
	public <K extends BaseLogDataElement> LogGenerator<K, REQUEST> create(K element, REQUEST request);
	
	public CONTEXT context();
}
