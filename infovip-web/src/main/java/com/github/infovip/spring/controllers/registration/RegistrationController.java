/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.spring.controllers.registration;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.web.DefaultStatusResponseBody;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.registration.CreateUser;
import com.github.infovip.core.web.response.SimpleStatusResponseGenerator;
import com.github.infovip.beans.user.UserManagement;
import com.github.infovip.beans.user.UserManagementLocal;
import com.github.infovip.entities.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * The RegistrationController responsible for the registration.
 * The registration-related functions are implemented here.
 * 
 * In case of successful registration a confirmation email will be sent
 * to the given email address which should be confirmed within 24 hours.
 * 
 * The confirmation is handled by the "/confirm/{key}" request. The key is an 
 * unique identifier that ensures that the user has a valid email address. @todo
 * 
 * The captcha ensures us that the user is real person, and not an OCR script
 * tries to create a fake account. @todo
 * 
 * The registration module also supports the facebook-login API, and google-oauth API. @todo
 *
 * @author attila
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    /**
     * UserManagement module
     */
    UserManagementLocal userManagement = lookupUserManagementLocal();

    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registrationForm() {
        ModelAndView mv = new ModelAndView("registration/registration");
        return mv;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
    }

    @RequestMapping(path = "/confirm/{key}")
    public ModelAndView confirm(@PathVariable(value = "key") String key) {
        ModelAndView mv = new ModelAndView("confirm");
        // todo
        return mv;
    }

    /**
     *
     *
     * @param user
     * @param request
     * @param response
     * @param result
     * @param status
     * @return
     */
    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    DefaultStatusResponseBody processSubmit(@ModelAttribute("user") User user, HttpServletRequest request,
            HttpServletResponse response, BindingResult result, SessionStatus status, Model model) {
        try {
            return SimpleStatusResponseGenerator.create(new CreateUser(request, userManagement));
        } catch (UnsupportedTypeException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @RequestMapping(path = "/addModel", method = RequestMethod.POST)
    public ModelAndView ModelAndView(@ModelAttribute("user") User user, HttpServletRequest request,
            HttpServletResponse response, BindingResult result, SessionStatus status, Model model) {
        throw new NotSupportedException("Not supported yet.", (Response) response);
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
