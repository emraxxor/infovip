package com.github.infovip.core.web.product;

import javax.servlet.annotation.WebServlet;

import com.github.infovip.core.Configuration;

/**
 * 
 * @author Attila Barna
 *
 */
@WebServlet("/public/media/image")
public class PublicMediaImage extends ImageServlet {

	public PublicMediaImage() {
        super(Configuration.MEDIA_IMAGE_PATH);
	}
}
