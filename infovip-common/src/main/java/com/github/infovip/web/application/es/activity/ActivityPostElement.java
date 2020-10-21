package com.github.infovip.web.application.es.activity;

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
public class ActivityPostElement extends ActivityElement {

	@Expose(serialize=true,deserialize=true)
	@IgnoreField
	private String date;

	@Expose(serialize=true,deserialize=false)
	@IgnoreField
	private String join;
	
	public ActivityPostElement() {
		super();
		this.date = DefaultDateFormatter.current();
		this.postType = ActivityJoinType.POST.value();
		this.join = ActivityJoinType.POST.value(); 
	}
	
	
}
