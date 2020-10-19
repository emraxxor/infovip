package com.github.infovip.core.web.product;

import javax.servlet.annotation.WebServlet;

import com.github.infovip.core.Configuration;

@WebServlet("/user/image")
public class UserImage extends ImageServlet {

	public UserImage() {
        super(Configuration.USER_IMAGE_PATH);
	}
}
