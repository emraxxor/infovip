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

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @author Attila Barna
 * @category infovip.core.data.configuration
 */
public class DefaultElasticsearchConfiguration {

	private Client client;
	
	private Settings settings;

	private ElasticsearchTemplate template;
	
	public DefaultElasticsearchConfiguration() {
	}
	
	public void postConstruct() {
		this.client = template.getClient();
		this.settings = client.settings();
	}
	
	public ElasticsearchTemplate getTemplate() {
		return template;
	}
	
	public void setTemplate(ElasticsearchTemplate template) {
		this.template = template;
	}
    
    public Client getClient() {
		return client;
	}
    
    public Settings getSettings() {
		return settings;
	}

}
