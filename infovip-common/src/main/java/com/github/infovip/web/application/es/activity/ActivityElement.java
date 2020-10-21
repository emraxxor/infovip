package com.github.infovip.web.application.es.activity;

import com.github.infovip.core.data.BaseDataElement;
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
public class ActivityElement extends BaseDataElement {

	@Expose(serialize=true,deserialize=true)
	protected Long uid;
	
	@Expose(serialize=true,deserialize=true)
	protected String userName;
	
	@Expose(serialize=true,deserialize=true)
	protected String userImage;
	
	@Expose(serialize=true,deserialize=true)
	protected String text;

	@Expose(serialize=true,deserialize=true)
	protected String postType;
	
	public ActivityElement() {
		super();
	}
}
