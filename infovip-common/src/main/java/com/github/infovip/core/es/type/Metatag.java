package com.github.infovip.core.es.type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Metatag {

	private String description;
	
	private String keyword;
	
	private String title;
	
	private String ogDescription;
	
	private String ogKeyword;
	
	private String ogTitle;
	
}
