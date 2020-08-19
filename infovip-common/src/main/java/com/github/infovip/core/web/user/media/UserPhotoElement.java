package com.github.infovip.core.web.user.media;

import java.io.Serializable;


import com.github.infovip.core.data.ESDataElement;
import com.github.infovip.core.es.type.IgnoreField;
import com.github.infovip.core.web.validation.AllowEmpty;

/**
 * 
 * @author Attila Barna
 *
 */
public class UserPhotoElement implements ESDataElement<UserPhotoElement>,  Serializable {

	@IgnoreField
	private String documentId;

	private String mediaId;

	private Long userId;

	private String data;
	
	@AllowEmpty
	private String title = "Untitled";

	public UserPhotoElement() {
		// TODO Auto-generated constructor stub
	}
	
	public UserPhotoElement(String parent, String title ,  Long userId, String image) {
		this.mediaId = parent;
		this.title = title;
		this.userId = userId;
		this.data = image;
	}
	
	
	@Override
	public String getDocumentId() {
		return this.documentId;
	}

	@Override
	public UserPhotoElement setDocumentId(String documentId) {
		this.documentId = documentId;
		return this;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		UserPhotoElement other = (UserPhotoElement) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
	
	
}
