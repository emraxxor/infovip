package com.github.infovip.core.web.product;

import javax.servlet.annotation.WebServlet;

import com.github.infovip.core.Configuration;

/**
 * Servlet implementation class UserImage
 */
@WebServlet("/public/user/image")
public class PublicUserImage extends ImageServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -3567858115759360813L;

	/**
     */
    public PublicUserImage() {
        super(Configuration.USER_IMAGE_PATH);
    }
}
