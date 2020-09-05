package com.github.infovip.core.validator;

import com.github.infovip.core.web.types.FormData;

/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public class DefaultFormValidator<T extends FormData> extends AbstractFormValidator<T> {

	public DefaultFormValidator(FormData data) {
		super(data);
	}

	@Override
	protected boolean validation() {
		return true;
	}
	
}
