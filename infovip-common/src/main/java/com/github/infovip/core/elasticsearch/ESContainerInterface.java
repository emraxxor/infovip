package com.github.infovip.core.elasticsearch;

import java.util.List;


/**
 * 
 * @author Attila Barna
 * @category infovip.core.elasticsearch.container
 * 
 * @todo @missingdoc
 * @param <T>
 */
public interface ESContainerInterface<T extends ESDataElement<?>> {

    public void add(T document);

    public void add(List<T> document);

	public void flush();

	public boolean isThereFreePlaceInTheQueue();
	
	public int queueSize();

	public int bulkSize();

	public Object executeThenGet(T data);
	
	public Object create(T data);
	
	public Object executeSynchronusRequest(T data);
	
	public Object createSynchronus(T data);
	
	public <TDATAELEMENT, TE extends ESDataElement<TDATAELEMENT>> void search(ESSimpleResquestElement<TDATAELEMENT, TE> element);
}
