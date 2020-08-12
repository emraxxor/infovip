package com.github.infovip.spring.controllers.user;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.core.data.DefaultUser;
import com.github.infovip.core.data.UserPublicFormElement;
import com.github.infovip.core.web.response.StatusResponse;
import com.github.infovip.core.web.types.ImageData;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;

/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/user/profile")
public class UserProfileController {

	@Autowired
    private UserServiceInterface<User> userService;

	@RequestMapping(path= "/update",method = {RequestMethod.GET, RequestMethod.POST })
    public  @ResponseBody Object updatePicture(
    		@RequestParam(name="picture",required=true) String base64EncodedImageData,
    		HttpServletRequest request, HttpServletResponse response,  
    		SessionStatus status, Model model) {
		 
		try {
			User current = userService.findById(  UserConfiguration.config(request).getId() );
			File f = ImageData.randomFileName(DefaultWebAppConfiguration.USER_IMAGE_PATH);
			
			System.out.println(f.getName());
			System.out.println(f.getCanonicalPath());
			
			if ( f.createNewFile() ) {
				
				if ( current.getPicture() != null ) 
					new File(DefaultWebAppConfiguration.USER_IMAGE_PATH + "/" + current.getPicture()).delete();
				
				ImageData.createThumbnail(base64EncodedImageData, f);
			}
			current.setPicture(f.getName());
			userService.save(current);
			return StatusResponse.success();
		} catch (IOException e) {
			e.printStackTrace();
			return StatusResponse.error(e);
		}
		
    }
	
    @RequestMapping(path= "/view",method = {RequestMethod.GET, RequestMethod.POST })
    public  @ResponseBody Object view(
    		@RequestParam(name="uid",required=true) Long uid,
    		HttpServletRequest request, HttpServletResponse response,  
    		SessionStatus status, Model model) {
    	return new UserPublicFormElement<User>( userService.findById(uid) );
    }
	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.profile.page");
		User current = userService.findById(  UserConfiguration.config(request).getId() );
    	mv.addObject("user", new DefaultUser<User>( current ) );
    	return mv;
    }

}
