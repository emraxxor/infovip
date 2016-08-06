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
     * Create an instance of User for testing
     *
     * @param userName
     * @return
     */
    private User createUser(String userName) {
        User u = new User(0L);
        u.setUserName(userName);
        u.setUserMail("testmail@mail.com");
        u.setUserPassword("password");
        LogRegistration lg = new LogRegistration(0L, "127.0.0.1", new Date(System.currentTimeMillis()));
        u.setLogRegistration(lg);
        lg.setUid(u);
        return u;
    }
    
    private void simpleTransaction(String uname) {
        System.out.println("CREATING USER!!!!!!!!!!!!!");
        User u = createUser(uname);
        u = userManagement.getUserService().save(u);
        User u1 = userManagement.getUserService().findByUserName(uname);
        System.out.println(u1.getUserName());
        userManagement.getUserService().delete(u1);
    }

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
        System.out.println("CALLING SIMPLE TRANSACTION!!!!!!!!!!!!!!!!!!!!!!!!");
        simpleTransaction("testuserName1");
        simpleTransaction("testuserName2");
        simpleTransaction("testuserName3");
        simpleTransaction("testuserName4");
        simpleTransaction("testuserName5");
        return "index";
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
        return "index";
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
