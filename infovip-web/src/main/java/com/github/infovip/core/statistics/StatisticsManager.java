package com.github.infovip.core.statistics;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration;
import com.github.infovip.core.browser.BrowserDetector;
import com.github.infovip.core.container.ESContainer;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.google.gson.Gson;

/**
 * The component that is responsible for maintaining statistical events.
 * 
 * @author attila
 *
 */
@Component
public class StatisticsManager {

	/**
	 * Default logger for the current component
	 */
	private Logger logger = Logger.getLogger(StatisticsManager.class);
	
	@Autowired
	private ESContainer<ESExtendedDataElement<?>> container;

    /**
     * Default template
     */
	@Autowired
    private ElasticsearchRestTemplate restTemplate;
    
	@Autowired
	private RestHighLevelClient restHighLevelClient;

	
	/**
	 * Represents the statistical data
	 **/
	public Map<String, StatisticalElement<StatisticalEventData<?>>> data;

	
	public StatisticsManager() {
		this.data = Collections.synchronizedMap(new LinkedHashMap<>());
	}
	
	
	public <T extends StatisticalEventData<?>> void addEvent(HttpServletRequest request, T event) {
		logger.info("Adding the following event :" + new Gson().toJson(event, StatisticalEventData.class) );
		synchronized (data) {
			if (  request.getHeader("User-Agent") != null && request.getHeader("User-Agent").equals("") == false && BrowserDetector.isBot(request.getHeader("User-Agent")) == false ) {
				if ( data.containsKey(request.getSession().getId() ) ) {
					data.get( request.getSession().getId() ).addEvent(event);
				} else {
					StatisticalElement<StatisticalEventData<?>> se = new StatisticalElement<>(request);
					se.addEvent(event);
					data.put(request.getSession().getId() , se);
				}
			}
		}
	}
	
	public void postConstruct() {
	}
	
	public void preDestroy() {
		data.clear();
	}
	
	
    @Scheduled(cron="0 */15 * * * *")
    public void synchronize() {
    	synchronized (data) {
    		for(StatisticalElement<StatisticalEventData<?>> e : data.values() ) {
    		
    			SearchRequest searchRequest = new SearchRequest(); 
    			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
    			searchSourceBuilder.query(
    					 QueryBuilders.boolQuery()
						  	.must(QueryBuilders.termQuery("sessionId", e.getSessionId() ))
						  	.must(QueryBuilders.termQuery("sessionCreated", e.getSessionCreated()))
    			); 
    			searchSourceBuilder.size(1);
    			searchRequest.source(searchSourceBuilder); 

    	    	SearchResponse response;
				try {
					response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

					if ( response.getHits().getTotalHits().value == 0L ) {
						container.add(e.getDataItem());
					}
					
					for( StatisticalEventData<?> o : e.getEvents() ) {
						logger.info("Adding to container: " +  new Gson().toJson(o,StatisticalEventData.class));
						container.add(o);
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    	
    		}
    		
    		data.clear();
		}
    }

	
}
