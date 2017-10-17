package com.github.infovip.core.web.xhttp;

public class DefaultXHTTPErrorMessage {

	private String message;
	
	private String messageType;
	
	public DefaultXHTTPErrorMessage() {
		this.messageType = "error";
	}
	
	public DefaultXHTTPErrorMessage(Exception e) {
		this();
		this.message = e.getMessage();
	}
	
	public DefaultXHTTPErrorMessage(String message) {
		this();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getMessageType() {
		return messageType;
	}
}
