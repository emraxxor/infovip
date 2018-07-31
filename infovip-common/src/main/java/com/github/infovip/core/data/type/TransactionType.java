package com.github.infovip.core.data.type;

public enum TransactionType {

	BALANCE_TOP_UP("BALANCE_TOP_UP"),
	
	GIFT_CREDIT("GIFT_CREDIT"),
	
	CLICK("CLICK"),
	
	VIEW("VIEW");
	
	private String value;
	
	private TransactionType(String v) {
		this.value = v;
	}
	
	
	public String val() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

}
