package com.github.infovip.core.web.user.media;

import java.io.Serializable;

import com.github.infovip.core.data.ESDataElement;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.IgnoreField;
import com.github.infovip.core.web.validation.AllowEmpty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Attila Barna
 *
 */
@Data
@NoArgsConstructor
public class UserPhotoElement implements ESDataElement<UserPhotoElement>,  Serializable {

	@IgnoreField
	private String documentId;

	private String mediaId;

	private Long userId;

	private String data;
	
	private String creationTime;
	
	private Integer width;
	
	private Integer height;
	
	@AllowEmpty
	private String title = "Untitled";
	
	public UserPhotoElement(String parent, String title ,  Long userId, String image) {
		this.mediaId = parent;
		this.title = title;
		this.userId = userId;
		this.creationTime = DefaultDateFormatter.current();
		this.data = image;
	}


	public UserPhotoElement setDocumentId(String id) {
		this.documentId = id;
		return this;
	}
	

}
