package com.github.infovip.web.application.es.activity;

import com.github.infovip.core.data.ESDefaultJoinRelation;
import com.github.infovip.core.date.DefaultDateFormatter;

/**
 * 
 * @author Attila Barna
 *
 */
public class ActivityCommentElement extends ActivityElement {

	private String date;

	private ESDefaultJoinRelation joinField;
	
	private String postType;
	
	public ActivityCommentElement(String parent) {
		this.postType = ActivityJoinType.COMMENT.value();
		this.joinField = new ESDefaultJoinRelation(ActivityJoinType.COMMENT.value(), parent);
		this.date = DefaultDateFormatter.current();
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @return the joinField
	 */
	public ESDefaultJoinRelation getJoinField() {
		return joinField;
	}

	/**
	 * @param joinField the joinField to set
	 */
	public void setJoinField(ESDefaultJoinRelation joinField) {
		this.joinField = joinField;
	}

	/**
	 * @return the postType
	 */
	public String getPostType() {
		return postType;
	}

	/**
	 * @param postType the postType to set
	 */
	public void setPostType(String postType) {
		this.postType = postType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((joinField == null) ? 0 : joinField.hashCode());
		result = prime * result + ((postType == null) ? 0 : postType.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		result = prime * result + ((userImage == null) ? 0 : userImage.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityCommentElement other = (ActivityCommentElement) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (joinField == null) {
			if (other.joinField != null)
				return false;
		} else if (!joinField.equals(other.joinField))
			return false;
		if (postType == null) {
			if (other.postType != null)
				return false;
		} else if (!postType.equals(other.postType))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		if (userImage == null) {
			if (other.userImage != null)
				return false;
		} else if (!userImage.equals(other.userImage))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	
	
}
