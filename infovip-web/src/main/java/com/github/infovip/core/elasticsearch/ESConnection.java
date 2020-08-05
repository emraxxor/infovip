/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.core.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest.AliasActions;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.DeleteAliasRequest;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import com.github.infovip.core.DefaultElasticsearchConfiguration;
import com.google.gson.Gson;

/**
 *
 * @author Attila Barna
 * @category infovip.core.data.configuration
 */
public class ESConnection {

    /**
     * Transportclient for ElasticSearch Engine
     */
    private RestHighLevelClient client;

    /**
     * Settings for bulkrequest
     */
    private Settings settings;

    /**
     * Instance of bulkrequest
     */
    private BulkRequest bulkRequest;

    /**
     * Default template
     */
    private ElasticsearchRestTemplate template;
    
    private Lock lock = new ReentrantLock();
    
    private DefaultElasticsearchConfiguration elasticsearchConfiguration;
    
    public ESConnection(DefaultElasticsearchConfiguration esConfiguration) {
        this.elasticsearchConfiguration = esConfiguration;
        this.client = elasticsearchConfiguration.getClient();
        this.template = elasticsearchConfiguration.getTemplate();
    }

    public void init() {
    	
    }

    /**
     * Prepares a request
     */
    public void preapreRequest() {
        bulkRequest = new BulkRequest();
    }
    
    /**
     * Closes the client
     */
    public void close() {
        try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public <T> T doQuery( Supplier<T> s ) {
    	try {
    		return s.get();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    public RestHighLevelClient getClient() {
        return client;
    }

    private void indices(Consumer<org.elasticsearch.client.indices.GetIndexResponse> c) {
		try {
			org.elasticsearch.client.indices.GetIndexRequest request = new org.elasticsearch.client.indices.GetIndexRequest("*");
			org.elasticsearch.client.indices.GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
			c.accept(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private List<String> indices() {
    	try {
			org.elasticsearch.client.indices.GetIndexRequest request = new org.elasticsearch.client.indices.GetIndexRequest("*");
			org.elasticsearch.client.indices.GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
			return  Arrays.asList(response.getIndices());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * Gets a list which contains the indices
     *
     * @param match Only values return which matching for the given value
     * @return
     */
    public synchronized String getIndex(String match) {
    	Optional<String> ind = indices().stream().filter(e -> e.indexOf(match) != -1 ).findAny();
    	if ( ind.isPresent() ) 
    		return ind.get();
    	return null;
    }

    /**
     * Gets a list which contains the aliases
     *
     * @param match Only values return which matching for the given value
     * @return
     */
    public synchronized List<String> getIndices(String match) {
        final List<String> indices = new ArrayList<String>();
        indices( e -> {
        	List<String> ic = Arrays.asList( e.getIndices() );
        	ic.stream().filter( f -> f.indexOf(match) != -1 ).forEach(indices::add); 
        }); 
        return indices;
    }

    /**
     * Gets a list which contains the indices
     *
     * @return
     */
    public synchronized List<String> getIndicesAsList() {
    	final List<String> indices = new ArrayList<String>();
    	indices( e -> Arrays.asList( e.getIndices() ).forEach(indices::add) );
    	return indices;
    }

    /**
     * Removes the given index
     *
     * @param indexName
     */
    public synchronized void removeIndex(String indexName) {
    	try {
			DeleteIndexRequest request = new DeleteIndexRequest("posts"); 
			request.timeout(TimeValue.timeValueMinutes(2)); 
			request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
			request.indicesOptions(IndicesOptions.lenientExpandOpen());
			AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
			if (delete.isAcknowledged()) {
			    Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("Index: %s has been successfully removed.", indexName));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Adds an alias to the given index
     *
     * @param aliasName
     * @param indexName
     */
    public synchronized void createAlias(String aliasName, String indexName) throws Exception {
    	try {
			IndicesAliasesRequest request = new IndicesAliasesRequest(); 
			AliasActions aliasAction = new AliasActions(AliasActions.Type.ADD)
			        					 	.index(indexName)
			        					 	.alias(aliasName); 
			request.addAliasAction(aliasAction); 
			request.timeout(TimeValue.timeValueMinutes(2));
			request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
			
			AcknowledgedResponse resp = client.indices().updateAliases(request, RequestOptions.DEFAULT);
			if (resp.isAcknowledged()) 
			        Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("Alias %s to index %s has been created successfully.", aliasName, indexName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Removes the given alias
     *
     * @param aliasName
     * @param indexName
     */
    public synchronized void removeAlias(String aliasName, String indexName) {
    	try {
			DeleteAliasRequest request = new DeleteAliasRequest(indexName, aliasName);
			request.setTimeout(TimeValue.timeValueMinutes(2));
			request.setMasterTimeout(TimeValue.timeValueMinutes(1));
			org.elasticsearch.client.core.AcknowledgedResponse resp = client.indices().deleteAlias(request, RequestOptions.DEFAULT);
			if (resp.isAcknowledged()) 
			    Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("Alias %s to index %s has been removed successfully.", aliasName, indexName));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * Aliases
     *
     * @return
     */
    public synchronized List<AliasMetaData> getAliases() {
    	final List<AliasMetaData> meta = new ArrayList<AliasMetaData>();
    	indices( e ->  e.getAliases().entrySet().stream().flatMap( x -> x.getValue().stream() ).forEach(meta::add)  );
    	return meta;
    }

    /**
     * Gets the aliases as a list
     *
     * @return
     */
    public synchronized List<String> getAliasesAsList() {
    	return getAliases().stream().map(e -> e.get().getAlias()).collect(Collectors.toList());
    }

    /**
     * Creates a new index from the given resources
     *
     * @param indexName
     * @throws IOException
     * @throws ElasticsearchException
     */
    public synchronized void createIndex(String indexName) throws IOException, ElasticsearchException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder() 
        	    .put("index.number_of_shards", 5)
        );
        request.setTimeout(TimeValue.timeValueMinutes(2));
        request.setMasterTimeout(TimeValue.timeValueMinutes(1));
        request.waitForActiveShards(ActiveShardCount.DEFAULT);
        org.elasticsearch.client.indices.CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        if ( createIndexResponse.isAcknowledged() ) 
        	Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("Index : %s has been successfully created.", indexName));
        	
    }

    /**
     * Checks whether the index exists or not
     *
     * @param match
     * @return
     */
    public synchronized boolean existsIndex(String match) {
    	return getIndices(match).size() > 0;
    }

    /**
     * Gets the documents number for the given index.
     *
     * @param indexName
     * @return
     */
    public synchronized long getDocumentsNumber(String indexName) {
        try {
			if (existsIndex(indexName)) {
				SearchRequest searchRequest = new SearchRequest(indexName); 
				SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
				searchSourceBuilder.query(QueryBuilders.matchAllQuery()); 
				searchRequest.source(searchSourceBuilder); 
				SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
				return response.getHits().getTotalHits().value;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }

    /**
     * Checks if the alias exists or not
     *
     * @param aname
     * @return
     */
    public synchronized boolean existAlias(String aname) {
    	return getAliasesAsList().contains(aname);
    }

    /**
     * Inserts a new row with specified type
     *
     * @param ob
     */
    public synchronized void putRow(String indexName, String type, Map<String, Object> ob) {
		IndexRequest request = new IndexRequest(indexName);
		request.setRefreshPolicy(RefreshPolicy.WAIT_UNTIL);
		//request.id(data.id());
		request.source(ob);
		bulkRequest.add( request );
    }

}
