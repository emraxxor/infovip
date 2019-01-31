package com.github.infovip.spring.controllers.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.web.user.UserInfo;

@Controller
@RequestMapping("/api/user")
public class UserInfoController {

	@SuppressWarnings("unchecked")
	@RequestMapping(path = "/current",  method = RequestMethod.POST)
	public @ResponseBody Object current(HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model)  {
		if ( UserConfiguration.config(request).isAuthenticated() )  {
			return new UserInfo( UserConfiguration.config(request).getSession() );
		}
		return null;
	}

}
