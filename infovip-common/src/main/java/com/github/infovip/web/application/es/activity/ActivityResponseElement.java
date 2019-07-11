package com.github.infovip.web.application.es.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author Attila Barna
 *
 */
public class ActivityResponseElement extends ActivityPostElement {

	@Expose(serialize=false,deserialize=false)
	private List<ActivityCommentElement> comments;
	

	@Expose(serialize=false,deserialize=false)
	private List<ActivityLikeElement> likes;
	
	@Expose(serialize=false,deserialize=false)
	private long totalLikeCount;
	
	public ActivityResponseElement() {
		super();
		this.comments = new ArrayList<>();
		this.likes = new ArrayList<>();
		this.totalLikeCount = 0L;
	}
	
	
	public void addCommentElement(ActivityCommentElement e) {
		this.comments.add(e);
	}
	
	public void setTotalLikeCount(long totalLikeCount) {
		this.totalLikeCount = totalLikeCount;
	}
	
	public long getTotalLikeCount() {
		return totalLikeCount;
	}
	
	/**
	 * @return the comments
	 */
	public List<ActivityCommentElement> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<ActivityCommentElement> comments) {
		this.comments = comments;
	}

	public void setLikes(List<ActivityLikeElement> likes) {
		this.likes = likes;
	}
	
	public void addLike(ActivityLikeElement e) {
		this.likes.add(e);
	}
	
	public List<?> getLikes() {
		return likes;
	}
	
	
}
