package com.github.infovip.spring.controllers.user;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.core.data.photo.MediaWaterfallSource;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.scroll.DefaultScrollResponse;
import com.github.infovip.core.scroll.ScrollResponseGenerator;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.user.media.UserMediaElement;

/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/user/{id}/media")
public class UserMediaController {
	
	
	@Autowired
	private ESContainerInterface<ESExtendedDataElement<?>> esContainer;
	
	@Autowired
	private WebApplicationContext context;

	@RequestMapping(path = { "/data" }, method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object result(
			@PathVariable("id") Long userId,
			@RequestParam(name = "token", defaultValue = "", required = false) String token,
			HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model) {
		MediaWaterfallSource source = new MediaWaterfallSource(context, token, userId );
		try {
			return ScrollResponseGenerator.generate(
					new DefaultScrollResponse<UserMediaElement, WebApplicationContext>(), source, request, response);
		} catch (UnsupportedTypeException e) {
			return null;
		}
	}
	 

    @RequestMapping( value= {"/album/{aid}"}, headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView album(
    		@PathVariable("id") Long userId, 
    		@PathVariable("aid") Optional<String> aid,
    		HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.photo.page");
    	return mv;
    }
	
    @RequestMapping(headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(
    		@PathVariable("id") Long userId, 
    		HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.media.page");
    	mv.addObject("MediaUserId", userId);
    	return mv;
    }
}
