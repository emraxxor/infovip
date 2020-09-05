package com.github.infovip.core.web.user.media;

import java.io.Serializable;

import com.github.infovip.core.data.ESDataElement;
import com.github.infovip.core.data.UserPublicFormElement;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.IgnoreField;
import com.github.infovip.core.web.types.FormData;
import com.github.infovip.core.web.validation.AllowEmpty;
import com.github.infovip.core.web.validation.NotEmpty;
import com.github.infovip.core.web.validation.Numeric;

/**
 * 
 * @author Attila Barna
 *
 */
public class UserPhotoCommentElement implements ESDataElement<UserPhotoCommentElement>, FormData, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3050745232108096366L;
	
	@IgnoreField
	@AllowEmpty
	private String documentId;

	@NotEmpty
	private String mediaId;

	@NotEmpty
	private String photoId;
	
	@AllowEmpty
	private String creationTime;

	@NotEmpty
	private String comment;
	
	@NotEmpty
	@Numeric
	private Long commenter;
	
	@NotEmpty
	private String commenterName;

	
	public UserPhotoCommentElement() {
		this.creationTime = DefaultDateFormatter.current();
	}

	public UserPhotoCommentElement(String photoId, String mediaId, Long user, String commenterName, String comment) {
		this.photoId = photoId;
		this.mediaId = mediaId;
		this.comment = comment;
		this.commenter = user;
		this.commenterName = commenterName;
		this.creationTime = DefaultDateFormatter.current();
	}		

	@Override
	public String getDocumentId() {
		return this.documentId;
	}

	@Override
	public UserPhotoCommentElement setDocumentId(String documentId) {
		this.documentId = documentId;
		return this;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}


	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCommenter() {
		return commenter;
	}

	public void setCommenter(Long commenter) {
		this.commenter = commenter;
	}

	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}

	


}
