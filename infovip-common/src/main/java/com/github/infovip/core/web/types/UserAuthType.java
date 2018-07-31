package com.github.infovip.core.web.types;

public enum UserAuthType {

	FACEBOOK("FACEBOOK"),
	GOOGLE("GOOGLE"),
	STANDARD("STANDARD");
	;
	
	private String value;
	
	private UserAuthType(String v) {
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
