package com.github.infovip.core.elasticsearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;

/**
 * Usage of the ElasticsearchTemplate is deprecated as of version 4.0, use ElasticsearchRestTemplate instead.

 * @author attila
 *
 */
@Component
public class DefaultElasticsearchTemplate extends ElasticsearchRestTemplate /** extends ElasticsearchTemplate  **/ {

	public DefaultElasticsearchTemplate(RestHighLevelClient client) {
		super(client);
	}
		
	/**
	public DefaultElasticsearchTemplate(DefaultElasticsearchConfiguration config,  EntityMapper mapper) {
		super(config.getClient(), mapper);
	}
	**/

}
