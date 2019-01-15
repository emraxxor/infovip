package com.github.infovip.core.web.product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.github.infovip.core.Configuration;

@WebServlet("/thumbnail")
public class ThumbnailImage extends ImageServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2031267893571445604L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThumbnailImage() {
		super(Configuration.BLOG_IMAGE_PATH);
	}

}
