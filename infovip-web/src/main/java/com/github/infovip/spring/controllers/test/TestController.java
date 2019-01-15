package com.github.infovip.spring.controllers.test;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {


	@Autowired
    ServletContext context;
	
    @Autowired
    private ApplicationContext appContext;

    public TestController() {
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultRequestMethod() {
        ModelAndView mv = new ModelAndView("test/default");
        return mv;
    }


}
