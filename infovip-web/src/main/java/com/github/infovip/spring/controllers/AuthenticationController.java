/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.spring.controllers;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.core.log.BaseLogElement;
import com.github.infovip.core.log.LogManager;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.spring.config.ApplicationUser;

/**
 *
 * @author attila
 */
@Controller
public class AuthenticationController {


    @Autowired
    private ApplicationContext appContext;


	@Autowired
	private LogManager<BaseLogElement<?>, WebApplicationContext, HttpServletRequest> logManager;
    
    @Autowired
    private UserServiceInterface<User> userService;
    
   
    /**
     * The default logger
     */
    private Logger logger = Logger.getLogger(AuthenticationController.class);
    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView authADMLogIn(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
    	ModelAndView mv = new ModelAndView("tile.login.page");
        return mv;
    }
    
   
    
    /**
     * Logout handler
     *
     * @param locale
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
   
    }

}
