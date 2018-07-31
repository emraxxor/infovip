package com.github.infovip.common.source;

import static com.github.infovip.core.lang.Translate.tr;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.infovip.common.source.marker.RequiredMarker;

/**
 * 
 * @author attila
 *
 */
public class SourceMarkerData {

	private Map<String,String> data;
	
	private List<String> required;
	
	public SourceMarkerData() {
		data = new LinkedHashMap<String,String>();
		required = new ArrayList<>();
	}
	
	public static SourceMarkerData create() {
		SourceMarkerData data = new SourceMarkerData();
		data.processDataFields();
		return data;
	}
	
	public void processDataFields() {
		Field[] fields = SourceMarker.class.getDeclaredFields();
		for(Field f : fields ) {
			
			if ( f.getAnnotation(RequiredMarker.class) != null ) {
				required.add(f.getName());
			}
			
			if ( f.getName().equals("serialVersionUID")) continue;
			data.put( f.getName(), tr("source." + f.getName()));
		}
		
		 data =  data.entrySet().stream()
				 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new)) ;
	}
	
	public Map<String, String> getData() {
		return data;
	}
	
	public List<String> getRequired() {
		return required;
	}
	
}
