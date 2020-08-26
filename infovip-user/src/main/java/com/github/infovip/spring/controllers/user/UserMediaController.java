package com.github.infovip.spring.controllers.user;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.configuration.DefaultWebAppConfiguration;
import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.core.data.DefaultDataElement;
import com.github.infovip.core.data.photo.MediaWaterfallSource;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.scroll.DefaultScrollResponse;
import com.github.infovip.core.scroll.ScrollResponseGenerator;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.types.ImageData;
import com.github.infovip.core.web.user.media.UserMediaElement;
import com.github.infovip.core.web.user.media.UserPhotoElement;

/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/user/media")
public class UserMediaController {
	
	
	 @Autowired
	 private ESContainerInterface<ESExtendedDataElement<?>> esContainer;
	
	 @Autowired
	private WebApplicationContext context;

	@RequestMapping(path = { "/data" }, method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Object result(@RequestParam(name = "token", defaultValue = "", required = false) String token,
			HttpServletRequest request, HttpServletResponse response, SessionStatus status, Model model) {
		MediaWaterfallSource source = new MediaWaterfallSource(context, token, UserConfiguration.config(request).getId());
		try {
			return ScrollResponseGenerator.generate(
					new DefaultScrollResponse<UserMediaElement, WebApplicationContext>(), source, request, response);
		} catch (UnsupportedTypeException e) {
			return null;
		}
	}
	 
	 @RequestMapping( value = { "/upload" } , method= {RequestMethod.POST })
	 public @ResponseBody Object upload(
	    		@RequestParam(name="id",required=true) String id, 
	    		@RequestParam(name="src",required=true) String src, 
	    		@RequestParam(name="name",required=true) String name, 
	    		HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
	    	
	    	File f = ImageData.randomFileName(DefaultWebAppConfiguration.MEDIA_IMAGE_PATH);
			File flarge = new File(DefaultWebAppConfiguration.MEDIA_IMAGE_PATH + "/" + f.getName()  + "_BIG");
	    	
			if ( f.createNewFile() ) 
				ImageData.createThumbnail(new String(Base64.decodeBase64(src)), f);
		
			if ( flarge.createNewFile() ) 
				ImageData.createLargeImage(new String(Base64.decodeBase64(src)), flarge);
			
			UserPhotoElement up = new UserPhotoElement(id,name,UserConfiguration.config(request).getId(),f.getName());
	    	IndexResponse ir = (IndexResponse) esContainer.executeSynchronusRequest( 
	    				new DefaultDataElement<UserPhotoElement>(up).setIndex(DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_PHOTO) );
	    	up.setDocumentId(ir.getId());
	    	return up;
	  }
	 
    @RequestMapping( value = { "/create" } , method= {RequestMethod.POST })
    public @ResponseBody Object create(
    		@RequestParam(name="name",required=false,defaultValue="Untitled") String name, 
    		HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	UserMediaElement ume = new UserMediaElement();
    	ume.setName(name);
    	ume.setUserId(UserConfiguration.config(request).getId());
    	IndexResponse ir = (IndexResponse) esContainer.executeSynchronusRequest( 
    				new DefaultDataElement<UserMediaElement>(ume).setIndex(DefaultWebAppConfiguration.ESConfiguration.USER_MEDIA_INDEX) );
    	ume.setDocumentId(ir.getId());
    	return ume;
    }

    @RequestMapping( value= {"/album/{aid}"}, headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView album(
    		@PathVariable("aid") Optional<String> aid,
    		HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.photo.page");
    	return mv;
    }
	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.media.page");
    	return mv;
    }
}
