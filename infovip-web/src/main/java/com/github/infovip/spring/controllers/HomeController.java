/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.spring.controllers;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author attila
 */
@Controller
public class HomeController {

    /**
     *
     * The welcome site.
     *
     * @param locale
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
        return "tile.index";
    }
    
    /**
    *
    * The default profile ste..
    *
    * @param locale
    * @param model
    * @return
    */
   @RequestMapping(value = "/profile", method = RequestMethod.GET)
   public String profile(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
       return "tile.main.profile";
   }


    /**
     *
     * The welcome site.
     *
     * @param locale
     * @param model
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
        return "tile.index";
    }


}
