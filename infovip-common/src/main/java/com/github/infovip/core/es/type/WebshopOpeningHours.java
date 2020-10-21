package com.github.infovip.core.es.type;

import lombok.Data;

/**
 * 
 * @author attila
 *
 */
@Data
public class WebshopOpeningHours {

	private OpeningHour monday;
	
	private OpeningHour tuesday;
	
	private OpeningHour wednesday;
	
	private OpeningHour thursday;
	
	private OpeningHour friday;
	
	private OpeningHour saturday;
	
	private OpeningHour sunday;

}
