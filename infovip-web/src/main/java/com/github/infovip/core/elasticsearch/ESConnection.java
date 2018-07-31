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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesResponse;
import org.elasticsearch.action.admin.indices.alias.exists.AliasesExistResponse;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import com.carrotsearch.hppc.ObjectContainer;
import com.carrotsearch.hppc.cursors.ObjectCursor;
import com.github.infovip.core.DefaultElasticsearchConfiguration;

/**
 *
 * @author Attila Barna
 * @category infovip.core.data.configuration
 */
public class ESConnection {

    /**
     * Transportclient for ElasticSearch Engine
     */
    private Client client;

    /**
     * Settings for bulkrequest
     */
    private Settings settings;

    /**
     * Instance of bulkrequest
     */
    private BulkRequestBuilder bulkRequest;

    /**
     * Default template
     */
    private ElasticsearchTemplate template;
    
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
        bulkRequest = client.prepareBulk();
    }
    
    public ElasticsearchTemplate getTemplate() {
		return template;
	}
    
    public void setTemplate(ElasticsearchTemplate template) {
		this.template = template;
	}

    /**
     * Closes the client
     */
    public void close() {
        client.close();
    }

    public Client getClient() {
        return client;
    }

    /**
     * Gets a list which contains the indices
     *
     * @param match Only values return which matching for the given value
     * @return
     */
    public synchronized String getIndex(String match) {
        ClusterStateResponse response = client.admin().cluster().prepareState().execute().actionGet();
        Iterator<ObjectCursor<String>> lindices = response.getState().metaData().getIndices().keys().iterator();
        while (lindices.hasNext()) {
            ObjectCursor<String> oc = lindices.next();
            String indexName = oc.value;
            if (indexName.indexOf(match) != -1) {
                return indexName;
            }
        }
        return null;
    }

    /**
     * Gets a list which contains the aliases
     *
     * @param match Only values return which matching for the given value
     * @return
     */
    public synchronized List<String> getIndices(String match) {
        List<String> indices = new ArrayList<String>();
        ClusterStateResponse response = client.admin().cluster().prepareState().execute().actionGet();
        Iterator<ObjectCursor<String>> lindices = response.getState().metaData().getIndices().keys().iterator();
        while (lindices.hasNext()) {
            ObjectCursor<String> oc = lindices.next();
            String indexName = oc.value;
            if (indexName.indexOf(match) != -1) {
                indices.add(indexName);
            }
        }
        return indices;
    }

    /**
     * Gets a list which contains the indices
     *
     * @return
     */
    public synchronized List<String> getIndicesAsList() {
        List<String> indices = new ArrayList<String>();
        ClusterStateResponse response = client.admin().cluster().prepareState().execute().actionGet();
        Iterator<ObjectCursor<String>> lindices = response.getState().metaData().getIndices().keys().iterator();
        while (lindices.hasNext()) {
            ObjectCursor<String> oc = lindices.next();
            String indexName = oc.value;
            indices.add(indexName);
        }
        return indices;
    }

    /**
     * Removes the given index
     *
     * @param indexName
     */
    public synchronized void removeIndex(String indexName) {
        DeleteIndexResponse delete = client.admin().indices().delete(new DeleteIndexRequest(indexName)).actionGet();
        if (delete.isAcknowledged()) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("Index: %s has been successfully removed.", indexName));
        }
    }

    /**
     * Adds an alias to the given index
     *
     * @param aliasName
     * @param indexName
     */
    public synchronized void createAlias(String aliasName, String indexName) throws Exception {
        AliasesExistResponse existResponse = client.admin().indices().prepareAliasesExist(aliasName).execute().actionGet();
        if (existResponse.exists() == false) {
            IndicesAliasesResponse resp = client.admin().indices().prepareAliases().addAlias(indexName, aliasName).execute().actionGet();
            if (resp.isAcknowledged()) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("Alias %s to index %s has been created successfully.", aliasName, indexName));
            }
        }
    }

    /**
     * Removes the given alias
     *
     * @param aliasName
     * @param indexName
     */
    public synchronized void removeAlias(String aliasName, String indexName) {
        IndicesAliasesResponse resp = client.admin().indices().prepareAliases().removeAlias(indexName, aliasName).execute().actionGet();
        if (resp.isAcknowledged()) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("Alias %s to index %s has been removed successfully.", aliasName, indexName));

        }
    }

    /**
     * Aliases
     *
     * @return
     */
    public synchronized ObjectContainer<List<AliasMetaData>> getAliases() {
        GetAliasesResponse response = client.admin().indices().getAliases(new GetAliasesRequest()).actionGet();
        ImmutableOpenMap<String, List<AliasMetaData>> res = response.getAliases();
        return res.values();
    }

    /**
     * Gets the aliases as a list
     *
     * @return
     */
    public synchronized List<String> getAliasesAsList() {
        List<String> aliases = new ArrayList<>();
        ObjectContainer<List<AliasMetaData>> laliases = getAliases();
        Iterator<ObjectCursor<List<AliasMetaData>>> e = laliases.iterator();
        while (e.hasNext()) {
            ObjectCursor<List<AliasMetaData>> oc = e.next();
            for (AliasMetaData am : oc.value) {
                aliases.add(am.getAlias());
            }
        }
        return aliases;
    }

    /**
     * Creates a new index from the given resources
     *
     * @param indexName
     * @throws IOException
     * @throws ElasticsearchException
     */
    public synchronized void createIndex(String indexName) throws IOException, ElasticsearchException {
        IndicesExistsResponse res = client.admin().indices().prepareExists(indexName).execute().actionGet();
        if (res.isExists() == false) {
            CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate(indexName);
            CreateIndexResponse resp = createIndexRequestBuilder.execute().actionGet();
            if (resp.isAcknowledged()) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, String.format("Index : %s has been successfully created.", indexName));

            }
        }
    }

    /**
     * Checks whether the index exists or not
     *
     * @param match
     * @return
     */
    public synchronized boolean existsIndex(String match) {
        ClusterStateResponse response = client.admin().cluster().prepareState().execute().actionGet();
        Iterator<ObjectCursor<String>> lindices = response.getState().metaData().getIndices().keys().iterator();
        while (lindices.hasNext()) {
            ObjectCursor<String> oc = lindices.next();
            String indexName = oc.value;
            if (indexName.contains(match)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the documents number for the given index.
     *
     * @param indexName
     * @return
     */
    public synchronized long getDocumentsNumber(String indexName) {
        if (existsIndex(indexName)) {
            SearchResponse response = client.prepareSearch(indexName).setQuery(QueryBuilders.matchAllQuery()).setSize(0).execute().actionGet();
            return response.getHits().getTotalHits();
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
        ClusterStateResponse response = client.admin().cluster().prepareState().execute().actionGet();
        return getAliasesAsList().contains(aname);
    }

    /**
     * Inserts a new row with specified type
     *
     * @param ob
     */
    public synchronized void putRow(String indexName, String type, Map<String, Object> ob) {
        bulkRequest.add(client.prepareIndex(indexName, type).setSource(ob));
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
