package com.github.infovip.spring.controllers.user.settings;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.configuration.UserConfiguration;
import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.form.data.DefaultUserFormData;
import com.github.infovip.core.lang.Translate;
import com.github.infovip.core.mail.DefaultMailConverter;
import com.github.infovip.core.smtp.SmtpClient;
import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.web.user.validator.GeneralSettingsValidator;


/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/user/settings/general")
public class GeneralSettingsController {

	 @Autowired
  	 private SmtpClient smtpClient;

	 @Autowired
	 private UserServiceInterface<User> userService;
	 
	
	 @RequestMapping(path = "/update", method = RequestMethod.PUT )
	 public @ResponseBody Object update(
	    				  @ModelAttribute DefaultUserFormData<User,LogRegistration> user,
	    		    	  @RequestParam("g-recaptcha-response") String gRecaptchaResponse,
	    				  BindingResult result, 
	    				  HttpServletRequest request, 
	    				  HttpServletResponse response,  
	    				  SessionStatus status, 
	    				  Model model
	    ) {
	    	
		  /**
	    	if ( ! GoogleCaptchaValidator.validate(gRecaptchaResponse, request.getRemoteAddr()) ) {
	    		List<ValidationResponse> resp = new ArrayList<ValidationResponse>();
	    		resp.add(new ValidationResponse(ValidationType.INVALID_CAPTCHA,Translate.tr("Invalid captcha")));
				return resp;
	    	}
	    	
	      **/
	    	
		 User current = (User) UserConfiguration.config(request).getSession().getUser();
	     GeneralSettingsValidator<DefaultUserFormData<User,LogRegistration>> fv = new GeneralSettingsValidator<DefaultUserFormData<User,LogRegistration>>(user,userService, current );
	    	
	    	if ( fv.validate() ) { 
	    		if ( user.getUserMail() != null ) {
	    			current.setCountry(user.getCountry());
	    			current.setCounty(user.getCounty());
	    			current.setCity(user.getCity());
	    			current.setFirstName(user.getFirstName());
	    			current.setLastName(user.getLastName());
	    			current.setFirstName(user.getFirstName());
	    			current.setUserPassword(user.getUserPassword());
	    			current.setUserMail(user.getUserMail());
	    			userService.save(current);
	    			
	    			smtpClient.sendHTML(
	    					new String[] { current.getUserMail() }, 
	    					Translate.tr("tr.registration.email.subject"), 
	    					DefaultMailConverter.RegistrationTemplate(new DefaultUserFormData(current))
	    			);
	    		}
	    	}
	    	
	    	return fv.responses();
	}

	
	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.user.settings.general.page");
    	return mv;
    }

}
