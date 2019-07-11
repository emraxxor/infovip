package com.github.infovip.web.application.es.activity;

import java.util.ArrayList;
import java.util.List;

import com.github.infovip.core.data.ESDefaultJoinRelation;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.IgnoreField;
import com.google.gson.annotations.Expose;

/**
 * 
 * @author Attila Barna
 *
 */
public class ActivityCommentElement extends ActivityElement {

	@IgnoreField
	private String date;

	@Expose(serialize=true,deserialize=false)
	@IgnoreField
	private ESDefaultJoinRelation join;
	
	@Expose(serialize=false,deserialize=false)
	private List<ActivityLikeElement> likes;
	
	@Expose(serialize=false,deserialize=false)
	private long totalLikeCount;
	
	@Expose(serialize=false,deserialize=false)
	private List<ActivityCommentElement> replies;

	public ActivityCommentElement() {
		this.likes = new ArrayList<>();
		this.replies = new ArrayList<>();
		this.totalLikeCount = 0L;
	}
	
	public ActivityCommentElement(String parent) {
		this.postType = ActivityJoinType.COMMENT.value();
		this.join = new ESDefaultJoinRelation(ActivityJoinType.COMMENT.value(), parent);
		this.setParentDocument(parent);
		this.date = DefaultDateFormatter.current();
		this.likes = new ArrayList<>();
		this.replies = new ArrayList<>();
		this.totalLikeCount = 0L;
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
	
	public void addReply(ActivityCommentElement e) {
		this.replies.add(e);
	}
	
	public List<ActivityCommentElement> getReplies() {
		return replies;
	}
	
	public void setReplies(List<ActivityCommentElement> replies) {
		this.replies = replies;
	}
	
	/**
	 * @return the likes
	 */
	public List<?> getLikes() {
		return likes;
	}

	public void addLike(ActivityLikeElement e) {
		this.likes.add(e);
	}
	
	/**
	 * @param likes the likes to set
	 */
	public void setLikes(List<ActivityLikeElement> likes) {
		this.likes = likes;
	}

	/**
	 * @return the totalLikeCount
	 */
	public long getTotalLikeCount() {
		return totalLikeCount;
	}

	/**
	 * @param totalLikeCount the totalLikeCount to set
	 */
	public void setTotalLikeCount(long totalLikeCount) {
		this.totalLikeCount = totalLikeCount;
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
		return true;
	}
	
	
	
}
