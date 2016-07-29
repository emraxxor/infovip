/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.spring.controllers;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.security.BasicSecureFunctions;
import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author attila
 */
@Controller
@RequestMapping("/Module")
public class ModuleController {

    @Autowired
    ServletContext context;

    @Autowired
    private ApplicationContext appContext;

    public ModuleController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView("modules/default");
        return mv;
    }

    @RequestMapping(value = "/{moduleDirectoryName}")
    public ModelAndView handleModule(@PathVariable("moduleDirectoryName") String moduleDirectoryName, HttpServletRequest request, HttpServletResponse response) {
        if (BasicSecureFunctions.directoryTraversalInputCheck(moduleDirectoryName)) {
            File f = new File(request.getSession().getServletContext().getRealPath(String.format("%s/%s/index.jsp", Configuration.DEFAULT_MODULE_DIRECTORY, moduleDirectoryName)));
            if (f.exists()) {
                ModelAndView v = new ModelAndView(String.format("modules/%s/index", moduleDirectoryName));
                v.addObject("moduleWebPath", Configuration.WEB_DIRECTORY);
                v.addObject("moduleContext", context);
                v.addObject("moduleResources", context.getContextPath() + "/" + Configuration.MODULE_DIRECTORY.substring(1) + "/" + moduleDirectoryName);
                v.addObject("modulePath", Configuration.DEFAULT_MODULE_DIRECTORY + "/" + moduleDirectoryName);
                return v;
            }
        }
        return new ModelAndView("modules/default");
    }

}
