package com.github.infovip.core.web.product;

import javax.servlet.annotation.WebServlet;

import com.github.infovip.core.Configuration;

@WebServlet("/user/media-image")
public class MediaImage extends ImageServlet {

	public MediaImage() {
        super(Configuration.MEDIA_IMAGE_PATH);
	}
}
