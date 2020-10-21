package com.github.infovip.core.es.type;

import com.github.infovip.core.data.BaseDataElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author attila
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WebshopInfromationType extends BaseDataElement {

	private Long companyId;
	
	private Long webshopId;
	
	private Long sourceId;

	/**
	 * Lowercase analyzer is used for this field!
	 */
	private String companyName;
	
	/**
	 * Lowercase analyzer is used for this field!
	 */
	private String webshopName;
	
	/**
	 * Lowercase analyzer is used for this field!
	 */
	private String sourceName;
	
	private String phone1;
	
	private String phone2;
	
	private String companyAddress;
	
	private String companyMail;
	
	private String mapAddress;
	
	private String companyDescription;
	
	private String imageData;
	
	private WebshopOpeningHours openingHours;
	
}
