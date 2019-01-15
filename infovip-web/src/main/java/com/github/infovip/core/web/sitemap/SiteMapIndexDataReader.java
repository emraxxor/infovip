package com.github.infovip.core.web.sitemap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class SiteMapIndexDataReader {

	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * 
	 * @deprecated
	 * {@link #read()} method is no longer used because the data-structure has been moved into the internal data-store
	 */
	public static SiteMapIndexData read() throws JsonParseException, JsonMappingException, IOException {
		InputStream is = SiteMapIndexDataReader.class.getClassLoader().getResourceAsStream("sitemap/sitemap-config.yaml");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        SiteMapIndexData smid = mapper.readValue(is, SiteMapIndexData.class);
        String[] chars = new String[] { " ", "," };
        
        for(SiteMapCategory smc : smid.getCategories()) {
    		List<String> result = new ArrayList<>();
        	    		
        	for(String s : smc.getMarker()) {
    			List<String> formatted = new ArrayList<>();

				/*for(String cchar : chars ) {
					String[] out;
					
					if ( formatted.size() == 0 )
						out = s.split(cchar);
					else
						out = (String[]) formatted.toArray(new String[formatted.size()]);
					
					formatted.addAll(Arrays.asList(out).stream().filter(o->!o.equalsIgnoreCase(cchar)).collect(Collectors.toList()));
					formatted = formatted.stream().map(o -> o.replaceAll(cchar, "")).collect(Collectors.toList());
					formatted = formatted.stream().map(String::toLowerCase).distinct().collect(Collectors.toList());
				}*/
    			
				formatted.add(s);
				//formatted = formatted.stream().filter(o->StringUtils.isAlphanumeric(o)).collect(Collectors.toList());
				formatted = formatted.stream().filter(o->o.length()>0).collect(Collectors.toList());
				result.addAll(formatted);
        	}
        	
    		result = result.stream().distinct().collect(Collectors.toList());
    		smc.setMarker(result);
        }
        return smid;
	}
	
	public static <T> SiteMapIndexData read(List<T> data) throws Exception {
		return SiteMapIndexData.create(data);
	}

}
