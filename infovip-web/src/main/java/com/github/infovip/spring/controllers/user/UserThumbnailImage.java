package com.github.infovip.spring.controllers.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.github.infovip.configuration.DefaultWebAppConfiguration;

@Controller
@RequestMapping("/public/image")
public class UserThumbnailImage {

	
	@RequestMapping(path = { "/user/{uid}" }, method =  RequestMethod.GET )
	public @ResponseBody Object getImageWithMediaType(
			@PathVariable(name = "uid") Long uid,
			HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model
			)  {
	
		File f = new File(DefaultWebAppConfiguration.USER_IMAGE_PATH + "/" + uid + "/thumbnail" );
		if ( f.exists() ) {
			try {
	    		response.setContentType("image/jpeg");  
				FileInputStream fin = new FileInputStream(f);
				return IOUtils.toByteArray(fin);
			} catch (IOException e) {
				return "Nothing is here :(";
			}
		}
		return "Nothing is here :(";
	}

}
