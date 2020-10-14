package com.github.infovip.spring.controllers.registration;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.github.infovip.core.data.UserPublicFormElement;
import com.github.infovip.core.date.DefaultDateFormatter;
import com.github.infovip.core.form.data.DefaultUserFormData;
import com.github.infovip.core.form.validators.RegistrationValidator;
import com.github.infovip.core.lang.Translate;
import com.github.infovip.core.mail.DefaultMailConverter;
import com.github.infovip.core.smtp.SmtpClient;
import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.spring.services.UserService;



/**
 * 
 * @author Attila Barna
 *
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private SmtpClient smtpClient;
	
	@Autowired
	private UserServiceInterface<User> userService;
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	private Logger logger = Logger.getLogger(RegistrationController.class);

	@RequestMapping(path = "/add", method = RequestMethod.POST )
	public @ResponseBody Object add(
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
	    	
	    	RegistrationValidator<DefaultUserFormData<User,LogRegistration>> fv = new RegistrationValidator<DefaultUserFormData<User,LogRegistration>>(user,userService);
	    	fv.setEncoder(pwEncoder);
	    	
	    	if ( fv.validate() ) { 
	    		if ( user.getUserMail() != null ) {
	    			User u = user.toUser(User.class, LogRegistration.class);
	    			u.getLogRegistration().setIp(request.getRemoteAddr());
	    			u.getLogRegistration().setCreationTime(DefaultDateFormatter.timestamp());
	    			u.setLastSeen(DefaultDateFormatter.timestamp());
	    			userService.save(u);
	    			
	    			smtpClient.sendHTML(
	    					new String[] { user.getUserMail() }, 
	    					Translate.tr("tr.registration.email.subject"), 
	    					DefaultMailConverter.RegistrationTemplate(user)
	    			);
	    		}
	    	}
	    	
	    	return fv.responses();
	}
	
	
    @RequestMapping( headers = "Accept=text/html",method=RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) throws IOException {
    	ModelAndView mv = new ModelAndView("tile.registration.page");
    	return mv;
    }

}
