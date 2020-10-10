package com.github.infovip.spring.controllers.user;

import java.io.File;
import java.io.IOException;

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
@RequestMapping("/user/{id}/profile")
public class UserProfileController {

	@Autowired
    private UserServiceInterface<User> userService;

	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(
    		@PathVariable("id") Long userId,
    		HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.profile.page");
		User current = userService.findById(  userId );
		
		if ( current == null ) 
			return new ModelAndView("redirect:/404");
		
    	mv.addObject("user", new DefaultUser<User>( current ) );
    	return mv;
    }

}
