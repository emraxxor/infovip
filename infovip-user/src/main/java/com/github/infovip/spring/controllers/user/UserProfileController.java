package com.github.infovip.spring.controllers.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController {

    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.profile.page");
    	return mv;
    }

}
