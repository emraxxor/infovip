package com.github.infovip.core.data;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author Attila Barna
 *
 *
 */
@Data
@Builder
public class Field {

	private String fieldName;
	
	private Object fieldValue;

	public Field(String fieldName, Object fieldValue) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
