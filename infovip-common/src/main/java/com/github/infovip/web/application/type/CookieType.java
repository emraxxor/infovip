package com.github.infovip.web.application.type;

public enum CookieType {

	TRANSACTION_CLICK_TOKEN_128("tctoken128");
	
	
	private String value;
	
	private CookieType(String v) {
		this.value = v;
	}
	
	
	public String val() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
	
	public static CookieType typeOf(String val) {
		for(CookieType t : CookieType.values()) {
			if ( t.val().equalsIgnoreCase(val) ) 
				return t;
		}
		
		return null;
	}

	
}
