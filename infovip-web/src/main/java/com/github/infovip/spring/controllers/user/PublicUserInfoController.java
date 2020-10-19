package com.github.infovip.spring.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.web.user.UserInfo;

@RestController
@RequestMapping("/public/user/info")
public class PublicUserInfoController {
	
	@Autowired
    private UserServiceInterface<User> userService;


	@GetMapping("/{id:[0-9]+}")
	public Object userInfo(@PathVariable(name = "id") Long id ) {
		User u = userService.findById(  id ) ;
		if ( u != null )
			return new UserInfo( u );
		return null;
	}
}
