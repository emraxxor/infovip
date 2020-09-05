package com.github.infovip.web.user.data.types;

import com.github.infovip.core.date.DefaultDateFormatter.DATE_FORMAT;
import com.github.infovip.core.es.type.EntityProperty;
import com.github.infovip.core.es.type.TimestampToString;
import com.github.infovip.core.web.types.FormElement;
import com.github.infovip.core.web.validation.AllowEmpty;
import com.github.infovip.core.web.validation.NotEmpty;
import com.github.infovip.entities.UserBlog;

/**
 * 
 * @author Attila Barna
 *
 */
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

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	
	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((bname == null) ? 0 : bname.hashCode());
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserBlogFormDataElement other = (UserBlogFormDataElement) obj;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (bname == null) {
			if (other.bname != null)
				return false;
		} else if (!bname.equals(other.bname))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
	
}
