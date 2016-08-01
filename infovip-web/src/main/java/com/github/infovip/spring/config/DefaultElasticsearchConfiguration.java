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
package com.github.infovip.spring.config;

import static com.github.infovip.core.Configuration.ELASTICSEARCH_CLIENT_ID;
import static com.github.infovip.core.Configuration.ELASTICSEARCH_CLIENT_SETTINGS;
import static com.github.infovip.core.Configuration.ELASTICSEARCH_TEMPLATE_NAME;
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
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 *
 * @author attila
 */
@Configuration("elasticsearchConfiguration")
@EnableElasticsearchRepositories(basePackages = "com.github.infovip.spring.elasticsearch.repositories")
@ComponentScan(basePackages = {"com.github.infovip.spring.services"})
public class DefaultElasticsearchConfiguration {

    @Bean
    public NodeBuilder nodeBuilder() {
        return new NodeBuilder();
    }

    @Bean(name = ELASTICSEARCH_CLIENT_SETTINGS)
    public Settings settings() {
        return Settings.settingsBuilder().put(ES_CLIENT_SETTINGS).build();
    }

    @Bean(name = ELASTICSEARCH_CLIENT_ID)
    public Client client() {
        try {
            Client client = TransportClient.builder().settings(settings()).build();
            for (String address : ES_CLIENT_OPTIONS.keySet()) {
                String port = ES_CLIENT_OPTIONS.get(address);
                ((TransportClient) client).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), Integer.valueOf(port)));
            }
            return client;
        } catch (UnknownHostException ex) {
            Logger.getLogger(DefaultElasticsearchConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Bean(name = ELASTICSEARCH_TEMPLATE_NAME)
    public ElasticsearchTemplate elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}
