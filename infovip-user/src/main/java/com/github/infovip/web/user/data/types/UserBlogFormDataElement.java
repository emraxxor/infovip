package com.github.infovip.web.user.data.types;

import com.github.infovip.core.date.DefaultDateFormatter.DATE_FORMAT;
import com.github.infovip.core.es.type.EntityProperty;
import com.github.infovip.core.es.type.TimestampToString;
import com.github.infovip.core.web.types.FormElement;
import com.github.infovip.core.web.validation.AllowEmpty;
import com.github.infovip.core.web.validation.NotEmpty;
import com.github.infovip.entities.UserBlog;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Attila Barna
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserBlogFormDataElement extends FormElement<UserBlog> {

	@AllowEmpty
	private Long bid;

	@NotEmpty
	private String bname;

	@TimestampToString(type = DATE_FORMAT.STRICT_DATE_TIME)
	private String creationTime;
	
	@EntityProperty(property = "userId")
	private Long user;

	public UserBlogFormDataElement() {
		// TODO Auto-generated constructor stub
	}
	
	public UserBlogFormDataElement(UserBlog o) {
		super(o);
	}

}
