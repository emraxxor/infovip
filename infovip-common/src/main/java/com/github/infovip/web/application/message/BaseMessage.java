package com.github.infovip.web.application.message;

import java.util.Date;

import com.github.infovip.core.data.BaseDataElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseMessage extends BaseDataElement {

	private Long userId;
	
	private String title;
	
	private String message;
	
	private Date time;
	
	private Boolean viewed;
	
	private MessageType type;
	
	private String to;
	
	public BaseMessage(Long userId, String to, String title, String message, MessageType type) {
		this.userId = userId;
		this.to = to;
		this.title = title;
		this.message = message;
		this.time = new Date();
		this.type = type;
		this.viewed = false;
	}

	
}
