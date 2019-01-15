package com.github.infovip.web.application.message;

/**
 * 
 * @author Attila Barna
 *
 */
public enum MessageType {

	SYSTEM_MESSAGE("SYSTEM_MESSAGE");
	
	private String type;
	
	private MessageType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	
}
