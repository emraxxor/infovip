package com.github.infovip.core.validator;

import com.github.infovip.core.web.types.FormData;


/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public abstract class AbstractFormValidator<T extends FormData> extends FormValidator<FormData> {

	public AbstractFormValidator(FormData data) {
		super(data);
	}

	protected abstract boolean validation();

	@Override
	public boolean validate() {
		return super.validate() && validation();
	}
}
