package com.github.infovip.core.elasticsearch;

import java.io.IOException;
import java.util.Map;

import org.springframework.core.convert.support.GenericConversionService;
//import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
//import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentProperty;
import org.springframework.data.mapping.context.MappingContext;

//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * Earlier versions of Spring Data Elasticsearch used a Jackson based conversion, Spring Data Elasticsearch 3.2.x introduced the Meta Model Object Mapping. As of version 4.0 only the Meta Object Mapping is used, the Jackson based mapper is not available anymore and the MappingElasticsearchConverter is used.
 *
 *
 * 
 * @author Attila Barna
 * @category infovip.core.data.configuration
 */
public class DefaultElasticsearchEntityMapper  extends MappingElasticsearchConverter /*extends ElasticsearchEntityMapper implements EntityMapper */ {

	public DefaultElasticsearchEntityMapper(MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext) {
		super(mappingContext);
	}

	/*
	private ObjectMapper objectMapper;

	public DefaultElasticsearchEntityMapper(MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext, GenericConversionService conversionService) {
		super(mappingContext, conversionService);
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	@Override
	public String mapToString(Object object) throws IOException {
		return objectMapper.writeValueAsString(object);
	}

	@Override
	public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
		return objectMapper.readValue(source, clazz);
	}

	@Override
	public Map<String, Object> mapObject(Object arg0) {
		return super.mapObject(arg0);
	}

	@Override
	public <T> T readObject(Map<String, Object> arg0, Class<T> arg1) {
		return super.readObject(arg0, arg1);
	}
	*/

}
