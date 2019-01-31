package com.github.infovip.spring.controllers.home;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.spring.components.DefaultMetaTagManager;


@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
    private ServletContext context;

    @Autowired
    private ApplicationContext appContext;
    
    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private DefaultMetaTagManager metaTagManager;
    
    @RequestMapping( path={"/", "/home"}, headers = "Accept=text/html")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) {
    	ModelAndView mv = new ModelAndView("tile.home.page");
    	metaTagManager.metaData("/home", mv);
    	return mv;
    }
}
