package com.github.infovip.web.application.es.activity;

import com.github.infovip.core.data.ESDefaultJoinRelation;
import com.github.infovip.core.date.DefaultDateFormatter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityCommentElementReply extends ActivityElement {

	private String date;

	private ESDefaultJoinRelation join;
	
	private String postType;

	public ActivityCommentElementReply(String parent) {
		this.postType = ActivityJoinType.REPLY.value();
		this.join = new ESDefaultJoinRelation(ActivityJoinType.REPLY.value(), parent);
		this.date = DefaultDateFormatter.current();
	}

	
}
