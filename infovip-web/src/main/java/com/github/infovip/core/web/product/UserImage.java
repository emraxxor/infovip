package com.github.infovip.core.web.product;

import javax.servlet.annotation.WebServlet;

import com.github.infovip.core.Configuration;

/**
 * Servlet implementation class UserImage
 */
@WebServlet("/user/image")
public class UserImage extends ImageServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -3567858115759360813L;

	/**
     */
    public UserImage() {
        super(Configuration.USER_IMAGE_PATH);
    }
}
