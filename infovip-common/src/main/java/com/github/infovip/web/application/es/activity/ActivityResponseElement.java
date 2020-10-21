package com.github.infovip.web.application.es.activity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Attila Barna
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
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
	
	public void addLike(ActivityLikeElement e) {
		this.likes.add(e);
	}
	
	
}
