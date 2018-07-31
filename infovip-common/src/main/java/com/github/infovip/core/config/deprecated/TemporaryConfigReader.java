package com.github.infovip.core.config.deprecated;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;


public class TemporaryConfigReader {

	public static TemporaryConfig readTemporaryConfiguration(InputStream is) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(is, TemporaryConfig.class);
	}
}
