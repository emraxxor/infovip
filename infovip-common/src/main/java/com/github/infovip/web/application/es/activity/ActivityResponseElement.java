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

	public ActivityResponseElement() {
		super();
		this.comments = new ArrayList<>();
	}
	
	
	public void addCommentElement(ActivityCommentElement e) {
		this.comments.add(e);
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
	
	
}
