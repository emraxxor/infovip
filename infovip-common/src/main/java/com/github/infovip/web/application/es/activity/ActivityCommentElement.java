package com.github.infovip.web.application.es.activity;

import java.util.ArrayList;
import java.util.List;

import com.github.infovip.core.data.ESDefaultJoinRelation;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.es.type.IgnoreField;
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

	
	public void addReply(ActivityCommentElement e) {
		this.replies.add(e);
	}
	

	public void addLike(ActivityLikeElement e) {
		this.likes.add(e);
	}
	
}
