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

import static com.github.infovip.core.Configuration.sessionValue;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.infovip.core.Configuration.SESSION;
import com.github.infovip.core.web.user.DefaultApplicationRole;
import com.github.infovip.core.web.user.UserSession;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;

/**
 *
 * @author attila
 */
@Controller
@Scope("request")
public class AuthenticationController {


    @Autowired
    private ApplicationContext appContext;

    @Autowired
    @Qualifier(value = "userSession")
    private UserSession userSession;
    
    
    @Autowired
    private UserServiceInterface<User> userService;
    
    /**
     * The default logger
     */
    private Logger logger = Logger.getLogger(AuthenticationController.class);
    
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String authADMLogIn(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
        return "tile.login.page";
    }
    
    /**
     * Authentication 
     *
     * @param userName
     * @param userPassword
     * @param locale
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login/do", method = RequestMethod.POST)
    public void login(
    		@RequestParam("email") String email,
    		@RequestParam("password") String password,
    		@RequestParam("g-recaptcha-response") String gRecaptchaResponse,
            Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
    	
    	/**
    	if ( ! GoogleCaptchaValidator.validate(gRecaptchaResponse, request.getRemoteAddr()) ) {
    		try {
				response.sendRedirect("/login?err=captcha");
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
    		return;
    	}
    	**/
    	
        try {
            User u = userService.findUserByEmail(email, password);
            if (u != null) {
                userSession.setAuthenticated(true);
                userSession.setUserId(u.getUserId());
                userSession.setUserName(u.getUserName());
                userSession.setUserMail(u.getUserMail());
                userSession.setRole(DefaultApplicationRole.SUPERADMIN);
                userSession.setRegistrationDate(u.getLogRegistration().getCreationTime());
                userSession.setUser(u);
                request.getSession().setAttribute(SESSION.USER_SESSION.toString(), userSession);
                request.getSession().setAttribute(SESSION.AUTH_TIME.toString(), new Date(System.currentTimeMillis()));
                request.getSession().setAttribute(SESSION.REMOTE_ADDR.toString(), request.getRemoteAddr());
                request.getSession().setAttribute(SESSION.HEADER.toString(), request.getHeader("User-Agent"));
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/login?err=invalid");
            }
        } catch (IOException ex) {
        	logger.error(ex.getMessage(),ex);
        }
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
        HttpSession session = request.getSession(false);
        if (session != null && request.isRequestedSessionIdValid()
                && session.getAttribute(sessionValue(SESSION.USER_SESSION)) != null
                && ((UserSession) session.getAttribute(sessionValue(SESSION.USER_SESSION))).isAuthenticated()) {
            try {
                /**
                 * @todo
                 * A nullpointer exception is occurred due to default filter settings.
                 */
                session.invalidate();
            } catch (NullPointerException e) {
                logger.info("Session has been already invalidated!");
            } catch (Exception e) {
            	logger.info("The WeldTerminalListener has no BeanManager injected and NPE is thrown");
            }
        }
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.sendRedirect("/");
    }

}
