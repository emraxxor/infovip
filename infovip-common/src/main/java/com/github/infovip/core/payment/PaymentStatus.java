package com.github.infovip.core.payment;

public enum PaymentStatus {

	PREPARED("PREPARED"),
	
	COMPLETED("COMPLETED");
	
	private String value;
	
	private PaymentStatus(String v) {
		this.value = v;
	}
	
	
	public String val() {
		return value;
	}
}
