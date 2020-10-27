package com.github.infovip.core.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FacebookUser {

	private String id;
	
	private String name;
	
	private String email;
	
	private String address;
	
	private String age_range;
	
	private String birthday;
	
	private String gender;
	
	private String hometown;
	
	private String first_name;
	
	private String last_name;
	
	private String middle_name;
	
	private String accessToken;
	
}
