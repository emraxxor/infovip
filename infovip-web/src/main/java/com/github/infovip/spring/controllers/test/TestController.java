package com.github.infovip.spring.controllers.test;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.security.BasicSecureFunctions;
import com.github.infovip.entities.User;
import com.github.infovip.spring.services.UserService;

@Controller
@RequestMapping("/test")
public class TestController {

	
	@Autowired
    ServletContext context;

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private UserService userService;
    
    public TestController() {
    }

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public ModelAndView defaultTestMethod() {
        ModelAndView mv = new ModelAndView("test/default");
    	Page<User> users = userService.findUsers(new PageRequest(0, 20));
    	System.out.println(users.getNumberOfElements());
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView defaultRequestMethod() {
        ModelAndView mv = new ModelAndView("test/default");
        return mv;
    }


    @RequestMapping(value = "/{moduleDirectoryName}")
    public ModelAndView handleModule(@PathVariable("moduleDirectoryName") String moduleDirectoryName, HttpServletRequest request, HttpServletResponse response) {
        if (BasicSecureFunctions.directoryTraversalInputCheck(moduleDirectoryName)) {
            File f = new File(request.getSession().getServletContext().getRealPath(String.format("%s/%s/index.jsp", Configuration.DEFAULT_TEST_DIRECTORY, moduleDirectoryName)));
            if (f.exists()) {
                ModelAndView v = new ModelAndView(String.format("test/%s/index", moduleDirectoryName));
                v.addObject("moduleWebPath", Configuration.WEB_DIRECTORY);
                v.addObject("moduleContext", context);
                v.addObject("moduleAppContext", appContext);;
                v.addObject("moduleResources", context.getContextPath() + "/test/" + moduleDirectoryName);
                v.addObject("modulePath", Configuration.DEFAULT_TEST_DIRECTORY + "/" + moduleDirectoryName);
                return v;
            }
        }
        return new ModelAndView("test/default");
    }

}
