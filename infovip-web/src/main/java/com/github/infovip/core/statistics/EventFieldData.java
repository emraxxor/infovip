package com.github.infovip.core.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The data field data that is stored in elasticsearch
 * @author attila
 *
 */
public class EventFieldData {

	private Map<String,Object> data;
	
	public EventFieldData() {
		this.data = new HashMap<>();
	}
	
	public EventFieldData add(String name,Object o) {
		if ( o instanceof List ) {
			if (((List) o).size() == 0) {
				return this;
			}
		}
		
		if ( o != null ) {
			this.data.put(name,o);
		}
		
		return this;
	}
	
	public static EventFieldData create() {
		return new EventFieldData();
	}
	
	public Map<String, Object> getData() {
		return data;
	}
}


