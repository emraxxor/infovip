package com.github.infovip.core.elasticsearch;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * Removal of the used Jackson Mapper
 * One of the changes in version 4.0.x is that Spring Data Elasticsearch does not use the Jackson Mapper anymore to map an entity to the JSON representation needed for Elasticsearch (see Elasticsearch Object Mapping). In version 3.2.x the Jackson Mapper was the default that was used. It was possible to switch to the meta-model based converter (named ElasticsearchEntityMapper) by explicitly configuring it (Meta Model Object Mapping).
 *
 * In version 4.0.x the meta-model based converter is the only one that is available and does not need to be configured explicitly. If you had a custom configuration to enable the meta-model converter by providing a bean like this:
 *
 *
 * 
 * @author Attila Barna
 * @category infovip.core.data.configuration
 */
public class DefaultObjectMapper /** implements EntityMapper **/ {

	private ObjectMapper objectMapper;

	/*public DefaultObjectMapper(MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> context) {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}*/
	
	public DefaultObjectMapper() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
		
	/**

	@Override
	public String mapToString(Object object) throws IOException {
		return objectMapper.writeValueAsString(object);
	}

	@Override
	public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
		return objectMapper.readValue(source, clazz);
	}

	@Override
	public Map<String, Object> mapObject(Object source) {
		try {
			return objectMapper.readValue(mapToString(source), HashMap.class);
		} catch (IOException e) {
			throw new MappingException(e.getMessage(), e);
		}
	}

	@Override
	public <T> T readObject(Map<String, Object> source, Class<T> targetType) {
		try {
			return mapToObject(mapToString(source), targetType);
		} catch (IOException e) {
			throw new MappingException(e.getMessage(), e);
		}
	}
	**/
}
