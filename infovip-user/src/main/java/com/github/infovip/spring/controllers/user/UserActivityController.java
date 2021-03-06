package com.github.infovip.spring.controllers.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.core.data.DefaultUser;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;

/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/user/{id}/activity")
public class UserActivityController {

	@Autowired
    private UserServiceInterface<User> userService;


	@GetMapping
    public ModelAndView main(
    		@PathVariable("id") Long userId,
    		HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	
		User current = userService.findById(  userId );

		if ( current == null ) 
			return new ModelAndView("redirect:/404");

		
    	ModelAndView mv = new ModelAndView("tile.user.activity.page");
    	mv.addObject("user", new DefaultUser<User>( current ) );
    	return mv;
    }
}
