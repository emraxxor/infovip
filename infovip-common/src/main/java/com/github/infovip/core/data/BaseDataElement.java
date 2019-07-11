package com.github.infovip.core.data;


/**
 * 
 * @author Attila Barna
 *
 */
public class BaseDataElement  {

	private String documentId = null;
	
	private String routing = null;
	
	private String parentDocument = null;
	
	public final BaseDataElement setRouting(String r) {
		this.routing = r;
		return this;
	}
	
	public final String routing() {
		return this.routing;
	}
	
	public final String getRouting() {
		return routing;
	}
	
	public final String getDocumentId() {
		return documentId;
	}
	
	public final BaseDataElement setDocumentId(String documentId) {
		this.documentId = documentId;
		return this;
	}
	
	public String getParentDocument() {
		return parentDocument;
	}
	
	public void setParentDocument(String parentDocument) {
		this.parentDocument = parentDocument;
	}


}
