package com.github.infovip.core.web.types;

public enum UserLanguage {

	HU("HU"),
	EN("EN"),
	;
	
	private String value;
	
	private UserLanguage(String v) {
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
