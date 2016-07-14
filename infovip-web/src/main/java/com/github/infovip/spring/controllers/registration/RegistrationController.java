/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.spring.controllers.registration;

import com.github.infovip.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author attila
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registrationForm() {
        ModelAndView mv = new ModelAndView("registration/registration");
        mv.addObject("user", new User());
        mv.addObject("k1", "default");
        return mv;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
    }

    @RequestMapping(path = "/confirm/{key}")
    public ModelAndView confirm() {
        ModelAndView mv = new ModelAndView("confirm");
        // todo
        return mv;
    }
    
    /**
     *
     * @param p1
     * @param model
     * @return
     */
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test(@RequestParam("p1") String p1, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("k1", p1);
        model.addAttribute("k2","pathtest");
        return "registration/registration";
    }


    @RequestMapping(path = "/add")
    public ModelAndView processSubmit(@ModelAttribute("user") User user, BindingResult result, SessionStatus status) {
        ModelAndView mv = new ModelAndView("registration/add");
        mv.addObject("test", user);
        return mv;
    }

}
