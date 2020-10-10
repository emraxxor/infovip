package com.github.infovip.spring.controllers.user;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.core.data.DefaultDataElement;
import com.github.infovip.core.data.photo.PhotoCommentSource;
import com.github.infovip.core.data.photo.PhotoWaterfallSource;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.scroll.DefaultScrollResponse;
import com.github.infovip.core.scroll.ScrollResponseGenerator;
import com.github.infovip.core.validator.DefaultFormValidator;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.response.StatusResponse;
import com.github.infovip.core.web.user.media.UserPhotoCommentElement;
import com.github.infovip.core.web.user.media.UserPhotoElement;

/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/user/{id}/photo")
public class UserPhotoController {
	
	
	 @Autowired
	 private ESContainerInterface<ESExtendedDataElement<?>> esContainer;
	
	 @Autowired
	private WebApplicationContext context;

	@RequestMapping(path = { "/data" }, method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object result(
			@PathVariable("id") Long userId,
			@RequestParam(name = "token", defaultValue = "", required = false) String token,
			@RequestParam(name = "aid", required = false) Optional<String> aid,
			HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model) {
		PhotoWaterfallSource source = new PhotoWaterfallSource(context, token, userId );
		
		if ( aid.isPresent() ) 
			source.setMediaId(aid.get());
				
		try {
			return ScrollResponseGenerator.generate(
					new DefaultScrollResponse<UserPhotoElement, WebApplicationContext>(), source, request, response);
		} catch (UnsupportedTypeException e) {
			return null;
		}
	}
	
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
	
	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.photo.page");
    	return mv;
    }


}
