package com.github.infovip.core.web.user;

/**
 * 
 * @author Attila Barna
 *
 */
public interface WebUser {

	/**
	 * Always represents the main identifier of the user
	 * @return
	 */
	public Long userId();
	
	
	/**
	 * The name or the mail of the current user 
	 * Sometimes it can be empty as there may be causes when the user doesn't have an email address 
	 * @return
	 */
	public String userIdentifier();

	
	/**
	 * The email-address of the current user
	 * @return
	 */
	public String userMailAddress();
	
}
