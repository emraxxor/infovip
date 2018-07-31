package com.github.infovip.core.es.type;

public enum Currency {

	HUF("HUF"),
	EUR("EUR"),
	USD("USD")
	;
	
	private String value;
	
	private Currency(String v) {
		value = v;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
