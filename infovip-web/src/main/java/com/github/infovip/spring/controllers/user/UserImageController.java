package com.github.infovip.spring.controllers.user;

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

import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.core.data.FileInfo;
import com.github.infovip.spring.services.StorageService;

@Controller
@RequestMapping("/api/user/image")
public class UserImageController {

	
	@Autowired
	private StorageService storageService;

	@RequestMapping(path="/upload", method = RequestMethod.POST)
	public @ResponseBody Object handleFileUpload(@RequestParam(name="data") String data, HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model)  {
		if ( UserConfiguration.config(request).isAuthenticated() )  {
			return new FileInfo(  storageService.storeFile(data) );
		}
		return null;
	}

}
