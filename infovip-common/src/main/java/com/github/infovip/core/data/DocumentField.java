package com.github.infovip.core.data;

/**
 * 
 * @author Attila Barna
 *
 */
public class DocumentField {

	private String fieldName;
	
	private String indexName;
	
	private String documentType;
	
	private Object documentValue;

	public DocumentField(String fieldName, String indexName, String documentType, Object documentValue) {
		super();
		this.fieldName = fieldName;
		this.indexName = indexName;
		this.documentType = documentType;
		this.documentValue = documentValue;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the indexName
	 */
	public String getIndexName() {
		return indexName;
	}

	/**
	 * @param indexName the indexName to set
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return the documentValue
	 */
	public Object getDocumentValue() {
		return documentValue;
	}

	/**
	 * @param documentValue the documentValue to set
	 */
	public void setDocumentValue(Object documentValue) {
		this.documentValue = documentValue;
	}
	
	
	
	
}
