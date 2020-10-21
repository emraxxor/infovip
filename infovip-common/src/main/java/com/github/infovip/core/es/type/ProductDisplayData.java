package com.github.infovip.core.es.type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author attila
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class ProductDisplayData<T extends DefaultDocumentType> {

	private String mainCategory;

	private String documentId;

	private Long companyId;
	
	private Long webshopId;
	
	private List<ImageMetaData> imageData;

	private String name;

	private String partNumber;

	private String description;

	private Long price;
	
	private Long sourceId;

	private String sourceName;
	
	private Map<String, List<String>> parameters;

	private List<String> parameterValues;

	private String url;
	
	private T document;
	
	public ProductDisplayData(T data) {
		document = data;
		url = data.getUrl();
		sourceId = data.getSourceId();
		sourceName = data.getOrigMetaData().getSourceName();
		documentId = data.getDocumentID();
		mainCategory = data.getMainCategory();
		imageData = data.getImageData();
		name = data.getName();
		partNumber = data.getPartNumber();
		description = data.getDescription();
		price = data.getPrice();
		parameters = data.getParameters();
		parameterValues = data.getParameterValues();
		companyId = data.getCompanyId();
		webshopId = data.getWebshopId();
		parameterValues.removeAll(Arrays.asList(null,""));
		parameters.values().removeIf(java.util.Objects::isNull);
		parameters = parameters.entrySet().stream().filter( e -> !e.getValue().equals("") ).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		parameters.entrySet().stream().forEach(e -> e.getValue().removeAll(Arrays.asList(null,"")) );
		
		if ( data.getDocumentID() == null ) 
			throw new RuntimeException("[FATAL] There is no id specified for the product!");
	}
	
	public T document() {
		return this.document;
	}
	
}
