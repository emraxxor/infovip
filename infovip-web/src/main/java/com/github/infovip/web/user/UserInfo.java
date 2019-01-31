package com.github.infovip.web.user;

import com.github.infovip.core.web.user.UserSessionInterface;
import com.github.infovip.entities.User;

/**
 * 
 * @author Attila Barna
 *
 */
public class UserInfo {

	private Long uid;
	
	private String userName;
	
	private String userImage;
	
	private String userMail;

	
	public UserInfo(UserSessionInterface<User> session) {
		this.uid = session.getUser().getUserId();
		this.userName = session.getUser().getUserRealName();
		this.userImage = "";
		this.userMail = session.getUser().getUserMail();
	}
	
	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userImage
	 */
	public String getUserImage() {
		return userImage;
	}

	/**
	 * @param userImage the userImage to set
	 */
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	/**
	 * @return the userMail
	 */
	public String getUserMail() {
		return userMail;
	}

	/**
	 * @param userMail the userMail to set
	 */
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	
	
	
}
