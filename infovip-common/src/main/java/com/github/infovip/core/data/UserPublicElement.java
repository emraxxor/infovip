package com.github.infovip.core.data;

import com.github.infovip.core.es.type.IgnoreField;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public class UserPublicElement<T> extends UserPublicFormElement<T> implements ESDataElement<UserPublicElement<T>> {
	
	@IgnoreField
	private String documentId;
	
	public UserPublicElement() {
		super();
	}

	@Override
	public String getDocumentId() {
		return this.documentId;
	}

	@Override
	public UserPublicElement<T> setDocumentId(String documentId) {
		this.documentId = documentId;
		return this;
	}
	
	

}
