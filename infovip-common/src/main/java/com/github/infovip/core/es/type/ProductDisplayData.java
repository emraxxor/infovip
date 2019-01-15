package com.github.infovip.core.es.type;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.github.infovip.core.es.type.DefaultDocumentType;
import com.github.infovip.core.es.type.ImageMetaData;

/**
 * 
 * @author attila
 *
 * @param <T>
 */
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
	
	public ProductDisplayData() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDisplayData(T data) {
		this();
		this.document = data;
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
		
		if ( data.getDocumentID() == null ) {
			throw new RuntimeException("[FATAL] There is no id specified for the product!");
		}
	}
	
	public T document() {
		return this.document;
	}
	
	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public List<ImageMetaData> getImageData() {
		return imageData;
	}

	public void setImageData(List<ImageMetaData> imageData) {
		this.imageData = imageData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}
	
	public String getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	
	public Long getSourceId() {
		return sourceId;
	}
	
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	
	public String getSourceName() {
		return sourceName;
	}
	
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public Map<String, List<String>> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, List<String>> parameters) {
		this.parameters = parameters;
	}

	public List<String> getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(List<String> parameterValues) {
		this.parameterValues = parameterValues;
	}
	
	

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the webshopId
	 */
	public Long getWebshopId() {
		return webshopId;
	}

	/**
	 * @param webshopId the webshopId to set
	 */
	public void setWebshopId(Long webshopId) {
		this.webshopId = webshopId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result + ((imageData == null) ? 0 : imageData.hashCode());
		result = prime * result + ((mainCategory == null) ? 0 : mainCategory.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parameterValues == null) ? 0 : parameterValues.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + ((partNumber == null) ? 0 : partNumber.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((webshopId == null) ? 0 : webshopId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDisplayData other = (ProductDisplayData) obj;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (imageData == null) {
			if (other.imageData != null)
				return false;
		} else if (!imageData.equals(other.imageData))
			return false;
		if (mainCategory == null) {
			if (other.mainCategory != null)
				return false;
		} else if (!mainCategory.equals(other.mainCategory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parameterValues == null) {
			if (other.parameterValues != null)
				return false;
		} else if (!parameterValues.equals(other.parameterValues))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (partNumber == null) {
			if (other.partNumber != null)
				return false;
		} else if (!partNumber.equals(other.partNumber))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		if (sourceName == null) {
			if (other.sourceName != null)
				return false;
		} else if (!sourceName.equals(other.sourceName))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (webshopId == null) {
			if (other.webshopId != null)
				return false;
		} else if (!webshopId.equals(other.webshopId))
			return false;
		return true;
	}
	
}
