package com.github.infovip.core.statistics;

/**
 * The type of the possible statistical element
 * @author attila
 *
 */
public enum StatisticsType {
	
	/**
	 * Event occurs if the product is opened directly eg. : /{p}/{name} 
	 */
	PRODUCT_OPENED("PRODUCT_OPENED","event-product"),
	
	
	/**
	 * This type of event is occured when the user clicks to the product
	 */
	PRODUCT_POPUP("PRODUCT_POPUP","event-product-popup"),
	
	/**
	 * It occurs when the user is redirected to the shop owner's site
	 */
	PRODUCT_REDIRECT("PRODUCT_REDIRECT","event-redirect"),
	
	/**
	 * It occurs when the user searches for something in the main form
	 */
	PRODUCT_SEARCH("PRODUCT_SEARCH","event-search");
	
	
	
	
	private String value;
	
	private String type;
	
	private StatisticsType(String v,String t) {
		this.value = v;
		this.type = t;
	}
	
	public String type() {
		return type;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public int indexOf(String v) {
		int i = 0;
		for ( StatisticsType e : StatisticsType.values() ) {
			if ( e.equals(v) ) {
				return i;
			}
		}
		return -1;
	}

}
