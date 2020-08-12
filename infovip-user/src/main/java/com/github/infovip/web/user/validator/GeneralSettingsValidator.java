package com.github.infovip.web.user.validator;

import com.github.infovip.core.form.data.DefaultUserFormData;
import com.github.infovip.core.lang.Translate;
import com.github.infovip.core.validator.FormValidator;
import com.github.infovip.core.web.response.ValidationResponse;
import com.github.infovip.core.web.types.ValidationType;
import com.github.infovip.entities.LogRegistration;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.util.BasicUtilities;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public class GeneralSettingsValidator<T extends DefaultUserFormData<User,LogRegistration>> extends FormValidator<T>  {

	private final UserServiceInterface<User> userService;
	
	private final User current;
	
	public GeneralSettingsValidator(T data, UserServiceInterface<User> service, User current) {
		super(data);
		this.userService = service;
		this.current = current;
	}
	
	@Override
	public boolean validate() {
		boolean result = super.validate();
		
		if ( !current.getUserMail().equals(this.data.getUserMail()) && userService.findUserByEmail(this.data.getUserMail()) != null ) {
			addResponse(new ValidationResponse(ValidationType.EMAIL_ALREADY_EXISTS, Translate.tr("Email already exists"), "email"));
			result = false;
		}

		if ( data.getOldPassword() != null && data.getOldPassword().length() > 0 &&  current.getUserPassword().equals(BasicUtilities.getMD5(data.getOldPassword()))  ) {
			
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
		
		} else {
			data.setUserPassword(current.getUserPassword());
		}
		
		data.setUserName(current.getUserName());
		
		return result;
	}

}
