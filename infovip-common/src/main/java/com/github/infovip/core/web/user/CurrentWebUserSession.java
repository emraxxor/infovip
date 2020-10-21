package com.github.infovip.core.web.user;

public interface CurrentWebUserSession<T> {

	/**
	 * Information about the current user
	 * @return
	 */
	public CurrentUserInfo<T> current();
}
