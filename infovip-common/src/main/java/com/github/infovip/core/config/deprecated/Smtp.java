package com.github.infovip.core.config.deprecated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Smtp {

	private String hostname;
	
	private int port;
	
	private String username;
	
	private String password;
	
	private boolean ssl;
	
	private String from;
	
	private String fromName;
	
	private boolean forceSSL;
	
	private boolean useStartTLS;
	
	
}
