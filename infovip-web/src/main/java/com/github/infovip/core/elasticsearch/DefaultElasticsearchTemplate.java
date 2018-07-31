package com.github.infovip.core.elasticsearch;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.github.infovip.core.DefaultElasticsearchConfiguration;

@Component
public class DefaultElasticsearchTemplate extends ElasticsearchTemplate {

	public DefaultElasticsearchTemplate(DefaultElasticsearchConfiguration config) {
		super(config.getClient(),new DefaultObjectMapper());
	}
}
