package com.github.infovip.web.user.data.types;

import com.github.infovip.core.lang.Translate;
import com.github.infovip.core.validator.FormValidator;
import com.github.infovip.core.web.response.ValidationResponse;
import com.github.infovip.core.web.types.ValidationType;

/**
 * 
 * @author Attila Barna
 *
 */
public class UserBlogFormDataElementValidator extends FormValidator<UserBlogFormDataElement> {

	public UserBlogFormDataElementValidator(UserBlogFormDataElement data) {
		super(data);
	}

	@Override
	public boolean validate() {
		boolean valid = super.validate();
		
		if ( valid ) {
			if ( data.getBname().isEmpty()  ) {
				addResponse(new ValidationResponse(ValidationType.EMPTY_FIELD, Translate.tr("validation.msg.field.empty"), "name"));
				return false;
			}
			
			if ( data.getBname().length() > 200  ) {
				addResponse(new ValidationResponse(ValidationType.EMPTY_FIELD, Translate.tr("validation.msg.field.long"), "name"));
				return false;
			}
		}
		
		return valid;
	}
}
