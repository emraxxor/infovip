package com.github.infovip.spring.controllers.gallery;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.core.model.UserPhotoElement;
import com.github.infovip.core.scroll.DefaultScrollResponse;
import com.github.infovip.core.scroll.ScrollResponseGenerator;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.response.StatusResponse;
import com.github.infovip.model.gallery.GallerySource;

/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private ElasticsearchRepository<UserPhotoElement, String> photoRepository;
	
	@RequestMapping(path = { "/data" }, method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object result(
			@RequestParam(name = "token", defaultValue = "", required = false) String token,
			HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model) {
		
		GallerySource source = new GallerySource(context, token);
		
		try {
			return ScrollResponseGenerator.generate(
					new DefaultScrollResponse<UserPhotoElement, WebApplicationContext>(), source, request, response);
		} catch (UnsupportedTypeException e) {
			return null;
		}
	}

	@PostMapping("/likes")
	public @ResponseBody Object likes(@RequestBody @Valid UserPhotoElement element, BindingResult res ) {
		if ( res.hasErrors() ) 
			return StatusResponse.error("Invalid data");

		Optional<UserPhotoElement> ue = photoRepository.findById(element.getDocumentId());
		
		if ( ! ue.isPresent() ) 
			return StatusResponse.error("Invalid data");

		return ue.get().getLikes();
	}
	
	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.gallery.page");
    	return mv;
    }

}
