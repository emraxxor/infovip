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

import com.github.infovip.beans.stateless.user.UserManagement;
import com.github.infovip.beans.stateless.user.UserManagementLocal;
import com.github.infovip.core.Configuration;
import com.github.infovip.core.Configuration.SESSION;
import static com.github.infovip.core.Configuration.sessionValue;
import com.github.infovip.core.web.user.UserSession;
import com.github.infovip.entities.User;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author attila
 */
@Controller
@Scope("request")
public class AuthenticationController {

    UserManagementLocal userManagement = lookupUserManagementLocal();

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    @Qualifier(value = "userSession")
    private UserSession userSession;

    /**
     * Authentication Failed Error handler
     *
     * @param locale
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/authfailed", method = RequestMethod.GET)
    public String authenticationFailed(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "tile.authfailed";
    }

    /**
     * Authentication Failed Error handler
     *
     * @param userName
     * @param userPassword
     * @param locale
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword,
            Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            User u = userManagement.findUser(userName, userPassword);
            if (u != null) {
                userSession.setAuthenticated(true);
                userSession.setUserName(u.getUserName());
                userSession.setUserMail(u.getUserMail());
                userSession.setRegistrationDate(u.getLogRegistration().getCreationTime());
                request.getSession().setAttribute(SESSION.USER_SESSION.toString(), userSession);
                request.getSession().setAttribute(SESSION.AUTH_TIME.toString(), new Date(System.currentTimeMillis()));
                request.getSession().setAttribute(SESSION.REMOTE_ADDR.toString(), request.getRemoteAddr());
                request.getSession().setAttribute(SESSION.HEADER.toString(), request.getHeader("User-Agent"));
                response.sendRedirect("home");
            } else {
                response.sendRedirect("authfailed");
            }
        } catch (IOException ex) {
            Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Session has been already invalidated!");
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "The WeldTerminalListener has no BeanManager injected and NPE is thrown");
            }
        }
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.sendRedirect("home");
    }

    private UserManagementLocal lookupUserManagementLocal() {
        try {
            Context c = new InitialContext();
            return (UserManagementLocal) c.lookup(Configuration.jndiLookupName(UserManagement.class.getSimpleName()));
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
