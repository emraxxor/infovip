package com.github.infovip.spring.controllers.pub.media;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.core.data.photo.PhotoCommentSource;
import com.github.infovip.core.scroll.DefaultScrollResponse;
import com.github.infovip.core.scroll.ScrollResponseGenerator;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.user.media.UserPhotoCommentElement;

@RestController
@RequestMapping("/public/media")
public class PublicMediaController {

    @Autowired
	private WebApplicationContext context;

	
	@RequestMapping(path = { "/comments" }, method = { RequestMethod.POST })
	public @ResponseBody Object result(
			@RequestParam(name = "token", defaultValue = "", required = false) String token,
			@RequestParam(name = "photoId", required = true) String photoId,
			HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model) {
		
		PhotoCommentSource source = new PhotoCommentSource(context, token, photoId);
		
		try {
			return ScrollResponseGenerator.generate(
					new DefaultScrollResponse<UserPhotoCommentElement, WebApplicationContext>(), source, request, response);
		} catch (UnsupportedTypeException e) {
			return null;
		}
	}
}
