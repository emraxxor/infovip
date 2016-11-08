/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.spring.controllers;

import com.github.infovip.beans.stateless.user.UserManagement;
import com.github.infovip.beans.stateless.user.UserManagementLocal;
import com.github.infovip.core.Configuration;
import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
     * UserManagement module
     */
    UserManagementLocal userManagement = lookupUserManagementLocal();

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
