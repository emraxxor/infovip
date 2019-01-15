package com.github.infovip.core.web.types;

public enum UserAccessType {

	NORMAL("NORMAL") ,
	COMPANY("COMPANY"),
	PENDING_APPROVAL("PENDING_APPROVAL")
	;
	
	private String value;
	
	private UserAccessType(String v) {
		this.value = v;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	public String value() {
		return this.value;
	}
	

}
