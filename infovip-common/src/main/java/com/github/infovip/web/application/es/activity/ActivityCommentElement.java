package com.github.infovip.web.application.es.activity;

import com.github.infovip.core.data.ESDefaultJoinRelation;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.google.gson.annotations.Expose;

/**
 * 
 * @author Attila Barna
 *
 */
public class ActivityCommentElement extends ActivityElement {

	private String date;

	@Expose(serialize=true,deserialize=false)
	private ESDefaultJoinRelation join;
	
	private String postType;
	
	public ActivityCommentElement(String parent) {
		this.postType = ActivityJoinType.COMMENT.value();
		this.join = new ESDefaultJoinRelation(ActivityJoinType.COMMENT.value(), parent);
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
	 * @return the join
	 */
	public ESDefaultJoinRelation getJoin() {
		return join;
	}

	/**
	 * @param join the join to set
	 */
	public void setJoin(ESDefaultJoinRelation join) {
		this.join = join;
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
		result = prime * result + ((join == null) ? 0 : join.hashCode());
		result = prime * result + ((postType == null) ? 0 : postType.hashCode());
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
		if (postType == null) {
			if (other.postType != null)
				return false;
		} else if (!postType.equals(other.postType))
			return false;
		return true;
	}
	
	
	
}
