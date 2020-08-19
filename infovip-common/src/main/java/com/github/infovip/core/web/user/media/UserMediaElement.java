package com.github.infovip.core.web.user.media;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.infovip.core.data.ESDataElement;
import com.github.infovip.core.es.type.IgnoreField;
import com.github.infovip.core.web.validation.AllowEmpty;

/**
 * 
 * @author Attila Barna
 *
 */
public class UserMediaElement implements ESDataElement<UserMediaElement>,  Serializable  {

	@IgnoreField
	private String documentId;
	
	@JsonProperty("userId")
	private Long userId;
	
	@AllowEmpty
	@JsonProperty("name")
	private String name;

	@IgnoreField
	private UserPhotoElement photo;

	public String getDocumentId() {
		return documentId;
	}

	public UserMediaElement setDocumentId(String documentId) {
		this.documentId = documentId;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserPhotoElement getPhoto() {
		return photo;
	}

	public void setPhoto(UserPhotoElement photo) {
		this.photo = photo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMediaElement other = (UserMediaElement) obj;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	

	
}
