package com.github.infovip.spring.controllers.user;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.core.data.DefaultUser;
import com.github.infovip.core.data.photo.PhotoCommentSource;
import com.github.infovip.core.data.photo.PhotoWaterfallSource;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.es.query.DocumentManager;
import com.github.infovip.core.model.UserPhotoElement;
import com.github.infovip.core.scroll.DefaultScrollResponse;
import com.github.infovip.core.scroll.ScrollResponseGenerator;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.response.StatusResponse;
import com.github.infovip.core.web.user.media.UserPhotoCommentElement;
import com.github.infovip.core.web.user.media.UserPhotoLike;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.user.CurrentUser;

import lombok.val;
import lombok.var;


/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/user/{id}/photo")
public class UserPhotoController {
	
	@Autowired
    private UserServiceInterface<User> userService;

	@Autowired
	private ESContainerInterface<ESExtendedDataElement<?>> esContainer;
	
	@Autowired
	private WebApplicationContext context;
	 
	@Autowired
	private DocumentManager documentManager;

	@Autowired
	private ElasticsearchRepository<UserPhotoElement, String> photoRepository;
	
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
	
	@PostMapping(path = "/like")
	public @ResponseBody Object like(@RequestBody @Valid UserPhotoElement element, BindingResult res ) {
		if ( res.hasErrors() ) 
			return StatusResponse.error("Invalid data");
		
		Optional<UserPhotoElement> ue = photoRepository.findById(element.getDocumentId());
		Long uid = CurrentUser.id();
		
		if ( ! ue.isPresent() ) 
			return StatusResponse.error("Invalid data");
		
		UserPhotoElement e = ue.get();
		var x = e.getLikes().stream().filter(f -> f.getUserId().equals(  uid) ) .findAny();
		if ( x.isPresent() ) {
			e.getLikes().remove(x.get());
		} else {
			e.getLikes().add( 
				UserPhotoLike
					.builder()	
						.userId(uid)
						.userName(CurrentUser.principal().current().userName())
					.build()
			);
		}
		
		photoRepository.save(e);
		return e.getLikes();
		
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
    public ModelAndView main(
    		@PathVariable("id") Long userId, 
    		HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.photo.page");
		User current = userService.findById(  userId );
		
		if ( current == null ) 
			return new ModelAndView("redirect:/404");

    	mv.addObject("user", new DefaultUser<User>( current ) );
    	return mv;
    }


}
