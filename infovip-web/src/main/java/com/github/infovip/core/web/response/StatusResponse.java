package com.github.infovip.core.web.response;

public class StatusResponse {

	public int code;
	
	public String statusType;
	
	public String message;
	
	
	
	public StatusResponse(int code, String statusType, String message) {
		this.code = code;
		this.message = message;
		this.statusType = statusType;
	}
	
	public StatusResponse(int code, String statusType) {
		this(code,statusType,"");
	}
	
	public static StatusResponse create(int code, String statusType) {
		return new StatusResponse(code, statusType);
	}

	
	public static StatusResponse create(int code, String statusType, String message) {
		return new StatusResponse(code, statusType, message);
	}

	
	public String getStatusType() {
		return statusType;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}
