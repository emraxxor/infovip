package com.github.infovip.spring.controllers.member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.scroll.DefaultScrollResponse;
import com.github.infovip.core.scroll.ScrollResponseGenerator;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.data.member.MemberWaterfallSource;
import com.github.infovip.web.application.es.activity.ActivityPostElement;


/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/members")
public class MembersController {
	
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	
	@Autowired
	private ESContainerInterface<ESExtendedDataElement<?>> esContainer;
	
	@Autowired
	private WebApplicationContext context;
	
	private Logger logger = Logger.getLogger(MembersController.class);


	
    @RequestMapping(path = {"/data"},method = {RequestMethod.GET, RequestMethod.POST })
    public  @ResponseBody Object result(
    		@RequestParam(name="token",defaultValue="",required=false) String token,
    		HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model
    ) {
    	MemberWaterfallSource source = new MemberWaterfallSource(context, token);
    	try {
			return ScrollResponseGenerator.generate( new DefaultScrollResponse<ActivityPostElement,WebApplicationContext>(),  source, request, response);
		} catch (UnsupportedTypeException e) {
			logger.error(e.getMessage(),e);
			return null;
		} 

    }


	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.members.general.page");
    	return mv;
    }

}
