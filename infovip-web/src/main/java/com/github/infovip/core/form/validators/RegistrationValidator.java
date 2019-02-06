package com.github.infovip.core.form.validators;

import com.github.infovip.core.form.data.DefaultUserFormData;
import com.github.infovip.core.lang.Translate;
import com.github.infovip.core.validator.FormValidator;
import com.github.infovip.core.web.response.ValidationResponse;
import com.github.infovip.core.web.types.ValidationType;
import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.spring.services.UserService;
import com.github.infovip.util.BasicUtilities;


/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public class RegistrationValidator<T extends DefaultUserFormData<User,LogRegistration>> extends FormValidator<T> {

	
	private final UserServiceInterface<User> userService;
	
	public RegistrationValidator(T data, UserServiceInterface<User> service) {
		super(data);
		this.userService = service;
	}
	
	@Override
	public boolean validate() {
		boolean result = super.validate();
		
		if ( userService.findUserByEmail(this.data.getUserMail()) != null ) {
			addResponse(new ValidationResponse(ValidationType.EMAIL_ALREADY_EXISTS, Translate.tr("Email already exists"), "email"));
			result = false;
		}
		
		if ( userService.findUserByName(this.data.getUserName()) != null ) {
			addResponse(new ValidationResponse(ValidationType.DATA_ALREADY_EXISTS, Translate.tr("Username already exists"), "userName"));
			result = false;
		}
		
		if ( data.getUserPassword() != null && data.getUserPassword().length() > 0 && data.getUserPasswordRepeat() != null && ! data.getUserPasswordRepeat().equals( data.getUserPassword() ) ) {
			addResponse(new ValidationResponse(ValidationType.INVALID_DATA, Translate.tr("Invalid data (password)"), "password1"));
			result = false;
		}
		
		if ( data.getUserPassword() != null && data.getUserPassword().length() > 0 && ( data.getUserPasswordRepeat() == null  || (data.getUserPasswordRepeat() != null && data.getUserPasswordRepeat().length() == 0) )  ) {
			addResponse(new ValidationResponse(ValidationType.INVALID_DATA, Translate.tr("Invalid data (password)"), "password2"));
			result = false;
		}
		
		if ( data.getUserPassword() != null ) 
			data.setUserPassword( BasicUtilities.getMD5(data.getUserPassword()) );
		
		
		return result;
	}
}
