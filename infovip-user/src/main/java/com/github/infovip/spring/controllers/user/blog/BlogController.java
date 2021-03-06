package com.github.infovip.spring.controllers.user.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.date.DefaultDateFormatter.DATE_FORMAT;
import com.github.infovip.entities.UserBlog;
import com.github.infovip.services.UserBlogService;
import com.github.infovip.user.CurrentUser;
import com.github.infovip.web.user.data.types.UserBlogFormDataElement;
import com.github.infovip.web.user.data.types.UserBlogFormDataElementValidator;

/**
 * Blogs are not stored in elasticsearch by default, just only the blog likes.
 * Blogs are not considered a highly visited site, therefore SQL database must be sufficient to serve the visitors.
 * 
 * @implNote 
 * Storage procedures may change in the future depending on the visitors.
 * 
 * @author Attila Barna
 *
 */
@RestController
@RequestMapping("/user/{id}/blog")
public class BlogController {
	
	@Autowired
	private UserBlogService blogService;

	@GetMapping("/list")
    public  Object list(
    		@PathVariable(name = "id") Long id,
    		@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
    		HttpServletRequest request, HttpServletResponse response,  
    		SessionStatus status, Model model) {
		return blogService.findAllByUser( id  ,  PageRequest.of( page , 12) ).stream().map( e -> new UserBlogFormDataElement(e));
	}
	
	
	@PutMapping("/update")
    public  Object update(
    		@RequestBody UserBlogFormDataElement data, 
    		HttpServletRequest request, HttpServletResponse response,  
    		SessionStatus status, Model model) {
		UserBlogFormDataElementValidator validator = new UserBlogFormDataElementValidator(data);
		if ( validator.validate() ) { 
			
			if ( data.getCreationTime() == null )
				data.setCreationTime(DefaultDateFormatter.current(DATE_FORMAT.STRICT_DATE_TIME));
			
			UserBlog ub = data.toDataElement(UserBlog.class);
			ub.setUserId( UserConfiguration.config(request).getId() );
			blogService.save(ub);
		}
		
		return validator.responses();	
	}
	
	@PostMapping("/create")
    public Object create(
    		@ModelAttribute UserBlogFormDataElement data, 
    		HttpServletRequest request, HttpServletResponse response,  
    		SessionStatus status, Model model) {
		
		UserBlogFormDataElementValidator validator = new UserBlogFormDataElementValidator(data);
		if ( validator.validate() ) {
			UserBlog ub = blogService.createBlog( CurrentUser.id() , data.getBname());
			return new UserBlogFormDataElement(ub);
		} 

		return validator.responses();
				
	}

}
