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
package com.github.infovip.core;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * @author Attila Barna
 * @category infovip.core.data.configuration
 */
public class DefaultElasticsearchConfiguration {

	private RestHighLevelClient client;
	
	private ElasticsearchRestTemplate template;
	
	public DefaultElasticsearchConfiguration(ElasticsearchRestTemplate template,RestHighLevelClient client) {
		this.template = template;
		this.client = client;
	}
	
	public void postConstruct() {
	}
	
	public ElasticsearchRestTemplate getTemplate() {
		return template;
	}
	
	public void setTemplate(ElasticsearchRestTemplate template) {
		this.template = template;
	}
    
    public RestHighLevelClient getClient() {
		return client;
	}
}
