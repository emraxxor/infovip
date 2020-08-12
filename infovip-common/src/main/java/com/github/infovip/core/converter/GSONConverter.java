package com.github.infovip.core.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Attila Barna
 *
 */
public class GSONConverter<T> {

	public GSONConverter() {
		// TODO Auto-generated constructor stub
	}
	
	public T convert(String json) {
		return new Gson().fromJson(json, new TypeToken<T>(){}.getType()  );
	}
	
	public String convert(T from) {
		return new Gson().toJson(from);
	}
}
