package com.github.infovip.core.config.deprecated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleAuth {
	
	private String clientId;
	
	private String projectId;
	
	private String authURI;
	
	private String authProvider;
	
	private String clientSecret;
	
}
