package com.github.infovip.core.web.types;

public enum UserGrantType {

	READER("READER"),
	EDITOR("EDITOR"),
	ADMIN("ADMIN")
	;
	
	private String value;
	
	private UserGrantType(String v) {
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
