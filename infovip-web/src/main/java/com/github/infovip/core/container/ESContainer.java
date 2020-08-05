package com.github.infovip.core.container;

import static com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration.BULK_WAIT_TIME;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
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
import com.google.gson.JsonSyntaxException;

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
    private ElasticsearchRestTemplate template;
	
	
	@Autowired
	private RestHighLevelClient client;
	
	/**
	 * Default document queue
	 */
	private LinkedBlockingQueue<T> documentQueue;
	
	
    /**
     * Instance of bulkrequest
     */
    private BulkRequest bulkRequest;

    
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
    	documentQueue = new LinkedBlockingQueue<>(ESConfiguration.QUEUE_MAX_SIZE);
    	preapreRequest();
		start();
	}
	
	
    /**
     * Prepares a request
     */
    public void preapreRequest() {
        bulkRequest = new BulkRequest();
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
		try {
			GetRequest gq = new GetRequest(e.index(), e.id());
			
			if ( e.routing() != null )
				gq.routing(e.routing());
			
			
			GetResponse r = client.get(gq, RequestOptions.DEFAULT);
			GsonBuilder gsonBuilder = new GsonBuilder();
			
			if ( e.exclusionStrategies() != null && e.exclusionStrategies().size() > 0 ) 
				e.exclusionStrategies().stream().forEach(o -> gsonBuilder.addDeserializationExclusionStrategy(o));
			
			Gson gson = gsonBuilder.create();
			
			TDATAELEMENT d = (TDATAELEMENT) gson.fromJson( r.getSourceAsString() , e.dataType());
			
			if ( d instanceof BaseDataElement )  
				((BaseDataElement) d).setDocumentId(r.getId());

			e.setResponse( (TDATAELEMENT) d);
		} catch (JsonSyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    }
    
    public synchronized Object executeSynchronusRequest(T data) {
		if ( data instanceof ESDataElement ) {
			try {
				if ( data.operation() == ESOperationType.DELETE ) {
					if ( data instanceof ESChildElement<?> ) {
						DeleteRequest dq = new DeleteRequest(data.index());
						dq.id(data.id());
						dq.routing(data.routing());
						dq.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
						// PARENT ?? DEPRECATED ??
						return client.delete(dq, RequestOptions.DEFAULT);
						//return client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setParent( ((ESChildElement<?>) data).parent() ).get();
					} else {					
						DeleteRequest dq = new DeleteRequest(data.index());
						dq.id(data.id());
						dq.routing(data.routing());
						dq.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
						return client.delete(dq, RequestOptions.DEFAULT);
						//return client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).get();
					}
				} else if ( data.operation() == ESOperationType.UPDATE ) {
					if ( data instanceof ESChildElement<?> ) {
						UpdateRequest request = new UpdateRequest(data.index(), data.id());
						request.doc(new Gson().toJson(data.data()), XContentType.JSON);
						request.routing(data.routing());
						request.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
						// PARENT ?? DEPRECATED ??
						return client.update(request, RequestOptions.DEFAULT);
						//return client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setParent(((ESChildElement<?>) data).parent()).setDoc(new Gson().toJson(data.data()), XContentType.JSON).get();
					} else {
						UpdateRequest request = new UpdateRequest(data.index(), data.id());
						request.routing(data.routing());
						request.doc(new Gson().toJson(data.data()), XContentType.JSON);
						request.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
						return client.update(request, RequestOptions.DEFAULT);
						//return client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setDoc(new Gson().toJson(data.data()), XContentType.JSON).get();
					}
				} else {
					if ( data instanceof ESChildElement<?> ) {
						if ( data.id() == null ) {
							IndexRequest request = new IndexRequest(data.index());
							request.routing(data.routing());
							request.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
							request.source(new Gson().toJson(data.data()) , XContentType.JSON);
							return client.index(request, RequestOptions.DEFAULT);
							//return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
						} else {
							IndexRequest request = new IndexRequest(data.index());
							request.routing(data.routing());
							request.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
							request.id(data.id());
							request.source(new Gson().toJson(data.data()) , XContentType.JSON);
							return client.index(request, RequestOptions.DEFAULT);
							//return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setId(data.id()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
						}
					} else if ( data.id() != null ) {
						IndexRequest request = new IndexRequest(data.index());
						request.routing(data.routing());
						request.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
						request.id(data.id());
						request.source(new Gson().toJson(data.data()) , XContentType.JSON);
						return client.index(request, RequestOptions.DEFAULT);

						//return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setId(data.id()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
					} else {
						
						IndexRequest request = new IndexRequest(data.index());
						request.routing(data.routing());
						request.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
						//request.id(data.id());
						request.source(new Gson().toJson(data.data()) , XContentType.JSON);
						return client.index(request, RequestOptions.DEFAULT);

						//return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setRefreshPolicy(RefreshPolicy.WAIT_UNTIL).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();									
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return null;
    }
    
    public synchronized Object executeThenGet(T data) {
		if ( data instanceof ESDataElement ) {
			try {
				if ( data.operation() == ESOperationType.DELETE ) {
					if ( data instanceof ESChildElement<?> ) {
						DeleteRequest dq = new DeleteRequest(data.index());
						dq.id(data.id());
						dq.routing(data.routing());
						// PARENT ?? DEPRECATED ??
						return client.delete(dq, RequestOptions.DEFAULT);
						//return client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).setParent( ((ESChildElement<?>) data).parent() ).get();
					} else {	
						DeleteRequest dq = new DeleteRequest(data.index());
						dq.id(data.id());
						dq.routing(data.routing());
						return client.delete(dq, RequestOptions.DEFAULT);
						//return client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).get();
					}
				} else if ( data.operation() == ESOperationType.UPDATE ) {
					if ( data instanceof ESChildElement<?> ) {
						UpdateRequest request = new UpdateRequest(data.index(), data.id());
						request.doc(new Gson().toJson(data.data()), XContentType.JSON);
						request.routing(data.routing());
						// PARENT ?? DEPRECATED ??
						return client.update(request, RequestOptions.DEFAULT);
						//return client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()).setDoc(new Gson().toJson(data.data()), XContentType.JSON).get();
					} else {
						UpdateRequest request = new UpdateRequest(data.index(), data.id());
						request.doc(new Gson().toJson(data.data()), XContentType.JSON);
						request.routing(data.routing());
						// PARENT ?? DEPRECATED ??
						return client.update(request, RequestOptions.DEFAULT);
						//return client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setDoc(new Gson().toJson(data.data()), XContentType.JSON).get();
					}
				} else {
					if ( data instanceof ESChildElement<?> ) {
						if ( data.id() == null ) {
							IndexRequest request = new IndexRequest(data.index());
							request.routing(data.routing());
							request.source(new Gson().toJson(data.data()) , XContentType.JSON);
							return client.index(request, RequestOptions.DEFAULT);
							//return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
						} else {
							IndexRequest request = new IndexRequest(data.index());
							request.routing(data.routing());
							request.id(data.id());
							request.source(new Gson().toJson(data.data()) , XContentType.JSON);
							return client.index(request, RequestOptions.DEFAULT);
							//return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setId(data.id()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
						}
					} else if ( data.id() != null ) {
						IndexRequest request = new IndexRequest(data.index());
						request.routing(data.routing());
						request.id(data.id());
						request.source(new Gson().toJson(data.data()) , XContentType.JSON);
						return client.index(request, RequestOptions.DEFAULT);
						//return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setId(data.id()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();
					} else {
						IndexRequest request = new IndexRequest(data.index());
						request.routing(data.routing());
						//request.id(data.id());
						request.source(new Gson().toJson(data.data()) , XContentType.JSON);
						return client.index(request, RequestOptions.DEFAULT);
						
						//return client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setSource(new Gson().toJson(data.data()),XContentType.JSON).get();									
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return null;
    }

   
	public synchronized void process(T data) {
		synchronized (bulkRequest) {
			if ( data instanceof ESDataElement ) {
				if ( data.operation() == ESOperationType.DELETE ) {
					if ( data instanceof ESChildElement<?>  ) {
						DeleteRequest dq = new DeleteRequest(data.index());
						dq.id(data.id());
						dq.routing(data.routing());
						// PARENT ?? DEPRECATED ??
						bulkRequest.add(dq);
						
						//bulkRequest.add( client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()) );
					} else {
						DeleteRequest dq = new DeleteRequest(data.index());
						dq.id(data.id());
						dq.routing(data.routing());
						bulkRequest.add(dq);
						//bulkRequest.add( client.prepareDelete(data.index(), data.type(), data.id() ).setRouting(data.routing()) );
					}
				} else if ( data.operation() == ESOperationType.UPDATE ) {
					if ( data instanceof ESChildElement<?> ) {
						UpdateRequest request = new UpdateRequest(data.index(), data.id());
						request.doc(new Gson().toJson(data.data()), XContentType.JSON);
						request.routing(data.routing());
						// PARENT ?? DEPRECATED ??
						bulkRequest.add(request);
						//bulkRequest.add( client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()).setDoc(new Gson().toJson(data.data()), XContentType.JSON) );
					} else {
						UpdateRequest request = new UpdateRequest(data.index(), data.id());
						request.doc(new Gson().toJson(data.data()), XContentType.JSON);
						request.routing(data.routing());
						bulkRequest.add(request);
						//bulkRequest.add( client.prepareUpdate(data.index(), data.type(), data.id() ).setRouting(data.routing()).setDoc(new Gson().toJson(data.data()), XContentType.JSON) );
					}
				} else {
					if ( data instanceof ESChildElement<?> ) {
						if ( data.id() == null ) {
							IndexRequest request = new IndexRequest(data.index());
							request.routing(data.routing());
							request.source(new Gson().toJson(data.data()) , XContentType.JSON);
							bulkRequest.add(request);
							//bulkRequest.add( client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON));
						} else {
							IndexRequest request = new IndexRequest(data.index());
							request.routing(data.routing());
							request.id(data.id());
							request.source(new Gson().toJson(data.data()) , XContentType.JSON);
							bulkRequest.add(request);
							//bulkRequest.add( client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setId(data.id()).setParent(((ESChildElement<?>) data).parent()).setSource(new Gson().toJson(data.data()),XContentType.JSON));
						}
					} else if (data.id() != null) { 
						IndexRequest request = new IndexRequest(data.index());
						request.routing(data.routing());
						request.id(data.id());
						request.source(new Gson().toJson(data.data()) , XContentType.JSON);
						bulkRequest.add(request);
						//bulkRequest.add( client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setId(data.id()).setSource(new Gson().toJson(data.data()),XContentType.JSON));									
					} else {
						IndexRequest request = new IndexRequest(data.index());
						request.routing(data.routing());
						request.source(new Gson().toJson(data.data()) , XContentType.JSON);
						bulkRequest.add(request);
						//bulkRequest.add( client.prepareIndex(data.index(), data.type()).setRouting(data.routing()).setSource(new Gson().toJson(data.data()),XContentType.JSON));									
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
					
					client.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
						
						@Override
						public void onResponse(BulkResponse response) {
							
						}
						
						@Override
						public void onFailure(Exception e) {
							logger.error("BULK FAILURE", e);
						}
					} );
					
					bulkCounter = 0;
					
					try {
						Thread.sleep(BULK_WAIT_TIME);
					} catch (InterruptedException e) {
						logger.error(e.getMessage(),e);
					}
					
					this.bulkRequest = new BulkRequest();
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