package com.github.infovip.core.web.types;

public enum UserReferenceType {

	COMPANY("COMPANY"),
	WEBSHOP("WEBSHOP")
	;
	
	
	private String value;
	
	private UserReferenceType(String v) {
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
