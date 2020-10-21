package com.github.infovip.core.config.deprecated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BarionConfiguration {

	private String baseuri;
	
	private String poskey;
	
	private String payee;
	
	private String module;

	private String applicationURL;
	
	private String contentType;
	
	private String host;
	
	private String userAgent;
	
	private String httpAccept;

	private String paymentStateURL;
	
}
