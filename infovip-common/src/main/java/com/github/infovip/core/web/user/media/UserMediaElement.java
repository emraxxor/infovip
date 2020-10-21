package com.github.infovip.core.web.user.media;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.infovip.core.data.ESDataElement;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.IgnoreField;
import com.github.infovip.core.web.validation.AllowEmpty;

import lombok.Data;

/**
 * 
 * @author Attila Barna
 *
 */
@Data
public class UserMediaElement implements ESDataElement<UserMediaElement>,  Serializable  {

	@IgnoreField
	private String documentId;
	
	@JsonProperty("userId")
	private Long userId;
	
	@AllowEmpty
	@JsonProperty("name")
	private String name;
	
	private String creationTime;


	@IgnoreField
	private UserPhotoElement photo;

	
	public UserMediaElement() {
		this.creationTime = DefaultDateFormatter.current();
	}
	
	public String getDocumentId() {
		return documentId;
	}

	public UserMediaElement setDocumentId(String documentId) {
		this.documentId = documentId;
		return this;
	}

	
}
