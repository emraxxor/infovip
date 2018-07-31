package com.github.infovip.core.es.type;

public enum CountryCode {

	HU("HU");
	
	private String value;
	
	private CountryCode(String v) {
		this.value = v;
	}
	
	@Override
	public String toString() {
		return this.value;
	};
	
}
