package com.github.infovip.core.payment;

public enum PaymentType {

	BARION("BARION");
	
	private String value;
	
	private PaymentType(String v) {
		this.value = v;
	}
	
	
	public String val() {
		return value;
	}

	public static PaymentType typeOf(String paymentType) {
		for( PaymentType p : PaymentType.values() ) {
			if ( p.equals(paymentType) ) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
