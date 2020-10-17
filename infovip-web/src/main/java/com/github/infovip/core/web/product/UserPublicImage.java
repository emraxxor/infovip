package com.github.infovip.core.web.product;

import javax.servlet.annotation.WebServlet;

import com.github.infovip.core.Configuration;

/**
 * 
 * @author Attila Barna
 *
 */
@WebServlet("/public/user/image")
public class UserPublicImage extends ImageServlet {

	public UserPublicImage() {
        super(Configuration.MEDIA_IMAGE_PATH);
	}
}
