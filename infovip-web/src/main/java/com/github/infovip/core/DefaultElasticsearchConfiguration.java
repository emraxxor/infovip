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

import static com.github.infovip.core.Configuration.ES_CLIENT_OPTIONS;
import static com.github.infovip.core.Configuration.ES_CLIENT_SETTINGS;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 *
 * @author attila
 */
public class DefaultElasticsearchConfiguration {

	private Client client;
	
	private Settings settings;
	
	public DefaultElasticsearchConfiguration() {
		System.out.println("------------------- Default Elasticsearch Configuration -------------------------");
		this.initSettings();
		this.initClient();
		System.out.println("------------------- Default Elasticsearch Configuration -------------------------");
	}
	

    public void initSettings() {
        this.settings = Settings.settingsBuilder().put(ES_CLIENT_SETTINGS).build();
    }
    
    public void initClient() {
        try {
            client = TransportClient.builder().settings(settings).build();
            for (String address : ES_CLIENT_OPTIONS.keySet()) {
                String port = ES_CLIENT_OPTIONS.get(address);
                ((TransportClient) client).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), Integer.valueOf(port)));
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(DefaultElasticsearchConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Client getClient() {
		return client;
	}
    
    public Settings getSettings() {
		return settings;
	}

}
