package com.github.infovip.core.container;

import static com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration.BULK_WAIT_TIME;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration;
import com.github.infovip.core.data.BaseDataElement;
import com.github.infovip.core.elasticsearch.ESChildElement;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.elasticsearch.ESOperationType;
import com.github.infovip.core.elasticsearch.ESSimpleResquestElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Attila Barna
 * @category infovip.core.data.manager
 */
@Component
public class ESContainer<T extends ESDataElement<?>> extends Thread implements ESContainerInterface<T>  {

	/**
	 * Default logger for the current component
	 */
	private Logger logger = Logger.getLogger(ESContainer.class);
	
    /**
     * Default template
     */
	@Autowired
    private ElasticsearchTemplate template;
	
	/**
	 * Default document queue
	 */
	private LinkedBlockingQueue<T> documentQueue;
	
	
    /**
     * Instance of bulkrequest
     */
    private BulkRequestBuilder bulkRequest;

    /**
     * The client
     */
    private Client client;
    
	/**
	 * A flag that counts how many elements are in the bulk
	 */
	private int bulkCounter = 0;


	/**
	 * The maximum size of the bulk
	 */
	private int bulkSize = 500;

	/**
	 * The default response for the request
	 */
    private BulkResponse bulkResponse;

    /**
     * Indicates whether the current thread is running or not
     */
    private Boolean isRunning = true;

	
	public ESContainer() {
		documentQueue = new LinkedBlockingQueue<>();
		bulkSize = ESConfiguration.BULK_SIZE;
	}

	
	public void postConstruct() {
    	client = template.getClient();
    	documentQueue = new LinkedBlockingQueue<>(ESConfiguration.QUEUE_MAX_SIZE);
    	preapreRequest();
		start();
	}
	
	
    /**
     * Prepares a request
     */
    public void preapreRequest() {
        bulkRequest = template.getClient().prepareBulk();
    } 

    public void preDestroy() {
    	terminate();
    }
	
	@Override
	public void run() {
		logger.info("Running  ESContainer...");
		while (isRunning) {
			T data = null;
			while (data == null) {
				try {
					data = documentQueue.take();
				} catch (InterruptedException e) {
					logger.info(String.format("ES-Container got interrupted while waiting for a new entry. Queue size is : %d ." , documentQueue.size()));
					logger.info("Tries again take a message.");
					logger.error(e);
				}
			}
			process(data);
		}
		logger.info("Destroying ESContainer...");
	}
	
	
    public synchronized void add(T document) {
    	while(document != null) {
    		try {
				documentQueue.put(document);
				document = null;
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
    	}
    }
    
    public synchronized void add(List<T> document) {
    	for(T o : document) {
    		add(o);
    	}
    }
   
    
    public synchronized Object create(T data) {
         Object response = executeThenGet(data);
         
         if ( response instanceof IndexResponse ) {
        	 Object o = data.data();
        	 
        	 if ( o instanceof BaseDataElement )
        		 ((BaseDataElement) o).setDocumentId(((IndexResponse)response).getId());
        	 
        	 return o;
         }
         
         throw new RuntimeException("Only Index operation is supported!");
    }
    
    public synchronized Object createSynchronus(T data) {
        Object response = executeSynchronusRequest(data);
        
        if ( response instanceof IndexResponse ) {
       	 Object o = data.data();
       	 
       	 if ( o instanceof BaseDataElement )
       		 ((BaseDataElement) o).setDocumentId(((IndexResponse)response).getId());
       	 
       	 return o;
        }
        
        throw new RuntimeException("Only Index operation is supported!");
   }

   
	@SuppressWarnings("unchecked")
	public synchronized <TDATAELEMENT, TE extends ESDataElement<TDATAELEMENT>> void search(ESSimpleResquestElement<TDATAELEMENT, TE> e) {
		GetRequestBuilder grb = client.prepareGet(e.index(),e.type(),e.id());
		
		if ( e.routing() != null )
			grb.setRouting(e.routing());
		
		GetResponse r = grb.get();
		GsonBuilder gsonBuilder = new GsonBuilder();
		
		if ( e.exclusionStrategies() != null && e.exclusionStrategies().size() > 0 ) 
			e.exclusionStrategies().stream().forEach(o -> gsonBuilder.addDeserializationExclusionStrategy(o));
		
		Gson gson = gsonBuilder.create();
		
		TDATAELEMENT d = (TDATAELEMENT) gson.fromJson( r.getSourceAsString() , e.dataType());
		
		if ( d instanceof BaseDataElement )  
			((BaseDataElement) d).setDocumentId(r.getId());
	
		e.setResponse( (TDATAELEMENT) d); 
    }
    
    public synchronized Object executeSynchronusRequest(T data) {
		if ( data instanceof ESDataElement ) {
			if ( data.operation() == ESOperationType.DELETE ) {
				if ( data instanceof ESChildElement<?> ) {
					return client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setParent( ((ESChildElement<?>) data).parent() ).get();
				} else {
					return client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).get();
				}
			} else if ( data.operation() == ESOperationType.UPDATE ) {
				if ( data instanceof ESChildElement<?> ) {
					return client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setParent(((ESChildElement<?>) data).parent()).setDoc(new Gson().toJson(data.data()), XContentType.JSON).get();
				} else {
					return client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setDoc(new Gson().toJson(data.data()), XContentType.JSON).get();
				}
			} else {
				if ( data instanceof ESChildElement<?> ) {
					if ( data.id() == null ) {
						return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
					} else {
						return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setId(data.id()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
					}
				} else if ( data.id() != null ) {
					return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setId(data.id()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
				} else {
					return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();									
				}
			}
		} 
		return null;
    }
    
    public synchronized Object executeThenGet(T data) {
		if ( data instanceof ESDataElement ) {
			if ( data.operation() == ESOperationType.DELETE ) {
				if ( data instanceof ESChildElement<?> ) {
					return client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).setParent( ((ESChildElement<?>) data).parent() ).get();
				} else {
					return client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).get();
				}
			} else if ( data.operation() == ESOperationType.UPDATE ) {
				if ( data instanceof ESChildElement<?> ) {
					return client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()).setDoc(new Gson().toJson(data.data()), XContentType.JSON).get();
				} else {
					return client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setDoc(new Gson().toJson(data.data()), XContentType.JSON).get();
				}
			} else {
				if ( data instanceof ESChildElement<?> ) {
					if ( data.id() == null ) {
						return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
					} else {
						return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setId(data.id()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
					}
				} else if ( data.id() != null ) {
					return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setId(data.id()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
				} else {
					return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();									
				}
			}
		} 
		return null;
    }

   
	public synchronized void process(T data) {
		synchronized (bulkRequest) {
			if ( data instanceof ESDataElement ) {
				if ( data.operation() == ESOperationType.DELETE ) {
					if ( data instanceof ESChildElement<?>  ) {
						bulkRequest.add( client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()) );
					} else {
						bulkRequest.add( client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()) );
					}
				} else if ( data.operation() == ESOperationType.UPDATE ) {
					if ( data instanceof ESChildElement<?> ) {
						bulkRequest.add( client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()).setDoc(new Gson().toJson(data.data()), XContentType.JSON) );
					} else {
						bulkRequest.add( client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setDoc(new Gson().toJson(data.data()), XContentType.JSON) );
					}
				} else {
					if ( data instanceof ESChildElement<?> ) {
						if ( data.id() == null ) {
							bulkRequest.add( client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON));
						} else {
							bulkRequest.add( client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setId(data.id()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON));
						}
					} else if (data.id() != null) { 
						bulkRequest.add( client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setId(data.id()).setSource(new Gson().toJson(data.data()),XContentType.JSON));									
					} else {
						bulkRequest.add( client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setSource(new Gson().toJson(data.data()),XContentType.JSON));									
					}
				} 
			} 
			
			bulkCounter++;
			
			if ( bulkCounter > bulkSize ) {
				flush();
			}
		}
	}
	
	
	public synchronized void flush() {
		synchronized (bulkRequest) {
				if ( bulkCounter > 0  ) {
					bulkResponse = bulkRequest.execute().actionGet();
					
					if ( bulkResponse.hasFailures() ) 
						logger.error(bulkResponse.buildFailureMessage());
					
					bulkCounter = 0;
					
					try {
						Thread.sleep(BULK_WAIT_TIME);
					} catch (InterruptedException e) {
						logger.error(e.getMessage(),e);
					}
					
					this.bulkRequest = client.prepareBulk();
				}
		}
	}
	
	public synchronized boolean isThereFreePlaceInTheQueue() {
		return queueSize() < bulkSize;
	}
	
	public synchronized int queueSize() {
		return documentQueue.size();
	}

	
	public synchronized int bulkSize() {
		return bulkCounter;
	}
	
    public synchronized void terminate() {
        isRunning = false;
        documentQueue.clear();
    }

}