package com.github.infovip.core.data;

import lombok.Data;

@Data
public class GoogleUser {

	public String profileId;
	
	public String givenName;
	
	public String familyName;
	
	public String email;
	
	public String name;
	
	public String id_token;

	public GoogleUser() {
		// TODO Auto-generated constructor stub
	}
	
}
