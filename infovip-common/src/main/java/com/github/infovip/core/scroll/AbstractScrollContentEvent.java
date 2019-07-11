package com.github.infovip.core.scroll;

/**
 * 
 * @author Attila Barna
 *
 * @param <DATA_ELEMENT>
 * @param <RESPONSE_ELEMENT>
 */
public abstract class AbstractScrollContentEvent<DATA_ELEMENT, RESPONSE_ELEMENT> implements ScrollContentEvent<DATA_ELEMENT, RESPONSE_ELEMENT> {

	protected RESPONSE_ELEMENT responseElement;
	
	
	public AbstractScrollContentEvent( RESPONSE_ELEMENT element ) {
		this.responseElement = element;
	}

}
