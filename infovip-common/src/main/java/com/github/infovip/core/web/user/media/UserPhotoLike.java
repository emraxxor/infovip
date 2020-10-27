package com.github.infovip.core.web.user.media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPhotoLike {

	private Long userId;
	
	private String userName;
	
	private String creationTime;
	
}
