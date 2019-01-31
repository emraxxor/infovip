package com.github.infovip.spring.controllers.activity;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.scroll.DefaultScrollResponse;
import com.github.infovip.core.scroll.ScrollResponseGenerator;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.user.UserSessionInterface;
import com.github.infovip.entities.User;
import com.github.infovip.web.application.es.activity.ActivityCommentElement;
import com.github.infovip.web.application.es.activity.ActivityPost;
import com.github.infovip.web.application.es.activity.ActivityPostElement;


/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ESContainerInterface<ESDataElement<?>> esContainer;
	
	@Autowired
	private WebApplicationContext context;
	
	private Logger logger = Logger.getLogger(ActivityController.class);
	
    @RequestMapping(path="/data",method = {RequestMethod.GET, RequestMethod.POST })
    public  @ResponseBody Object data(
    		@RequestParam(name="token",defaultValue="",required=false) String token,
    		HttpServletRequest request, 
    		HttpServletResponse response,  
    		SessionStatus status, 
    		Model model
    		) {
    	ActivitySource source = new ActivitySource(context, token);
    	try {
			return ScrollResponseGenerator.generate( new DefaultScrollResponse<ActivityPostElement,WebApplicationContext>(),  source, request, response);
		} catch (UnsupportedTypeException e) {
			logger.error(e.getMessage(),e);
			return null;
		} 
    }
    
    
    @RequestMapping(path="/comment", method = RequestMethod.POST)
    public @ResponseBody Object comment(
    		@RequestParam String id,
    		@RequestParam String text,
    		BindingResult result, 
    		HttpServletRequest request, 
    		HttpServletResponse response,  
    		SessionStatus status, 
    		Model model
    ) {
    	@SuppressWarnings("unchecked")
		UserSessionInterface<User> usession = UserConfiguration.config(request).getSession();
    	User u = usession.getUser();
    	ActivityCommentElement ace = new ActivityCommentElement(id);
    	ace.setUid(u.getUserId());
    	ace.setUserName(u.getUserRealName());
    	ace.setText(text);
    	ActivityPost<ActivityCommentElement> doc = new ActivityPost<ActivityCommentElement>(ace);
    	esContainer.executeThenGet(doc);
    	return null;
    }

	
    @RequestMapping(path="/add", method = RequestMethod.POST)
    public @ResponseBody Object add(
    		@ModelAttribute ActivityPostElement post,
    		BindingResult result, 
    		HttpServletRequest request, 
    		HttpServletResponse response,  
    		SessionStatus status, 
    		Model model
    ) {
    	@SuppressWarnings("unchecked")
		UserSessionInterface<User> usession = UserConfiguration.config(request).getSession();
    	User u = usession.getUser();
    	post.setUid(u.getUserId());
    	post.setUserName(u.getUserRealName());
    	ActivityPost<ActivityPostElement> doc = new ActivityPost<ActivityPostElement>(post);
    	esContainer.executeThenGet(doc);
    	return null;
    }
	
	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.activity.page");
    	return mv;
    }

}
