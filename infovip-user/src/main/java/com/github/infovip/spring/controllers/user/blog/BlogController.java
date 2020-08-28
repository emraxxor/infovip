package com.github.infovip.spring.controllers.user.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.github.infovip.configuration.EJBConfiguration;
import com.github.infovip.configuration.EJBConfiguration.EJB_MODULE;
import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.core.BlogManager;
import com.github.infovip.entities.UserBlog;
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
@Controller
@RequestMapping("/user/blog")
public class BlogController {
	
	private BlogManager bm = EJBConfiguration.lookupLocal(BlogManager.class, EJB_MODULE.INFOVIP_USER);
	
	
	@RequestMapping(path= "/update",method = {RequestMethod.GET, RequestMethod.POST })
    public  @ResponseBody Object update(
    		@ModelAttribute UserBlogFormDataElement data, 
    		HttpServletRequest request, HttpServletResponse response,  
    		SessionStatus status, Model model) {
		UserBlogFormDataElementValidator validator = new UserBlogFormDataElementValidator(data);
		if ( validator.validate() ) { 
			UserBlog ub = data.toDataElement(UserBlog.class);
			ub.setUserId( UserConfiguration.config(request).getId() );
			bm.update(ub);
		}
		
		return validator.responses();	
	}
	
	@RequestMapping(path= "/create",method = {RequestMethod.GET, RequestMethod.POST })
    public  @ResponseBody Object create(
    		@ModelAttribute UserBlogFormDataElement data, 
    		HttpServletRequest request, HttpServletResponse response,  
    		SessionStatus status, Model model) {
		
		UserBlogFormDataElementValidator validator = new UserBlogFormDataElementValidator(data);
		if ( validator.validate() ) {
			UserBlog ub = bm.createBlog(UserConfiguration.config(request).getId(), data.getBname());
			return new UserBlogFormDataElement(ub);
		} 

		return validator.responses();
				
	}

}
