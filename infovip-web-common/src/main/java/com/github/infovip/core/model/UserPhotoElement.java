package com.github.infovip.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.github.infovip.configuration.DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_PHOTO;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.github.infovip.core.data.ESDataElement;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.IgnoreField;
import com.github.infovip.core.web.user.media.UserPhotoLike;
import com.github.infovip.core.web.validation.AllowEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Attila Barna
 *
 */
@Document(indexName = USER_MEDIA_PHOTO)
@Data
@NoArgsConstructor
public class UserPhotoElement implements ESDataElement<UserPhotoElement>,  Serializable {

	@IgnoreField
	@NotNull
    @Id
	private String documentId;

	private String mediaId;

	private Long userId;

	private String data;
	
	private List<UserPhotoLike> likes = new ArrayList<>();
	
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
