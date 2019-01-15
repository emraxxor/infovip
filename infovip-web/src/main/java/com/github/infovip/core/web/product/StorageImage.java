package com.github.infovip.core.web.product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.github.infovip.core.Configuration;

@WebServlet("/file")
public class StorageImage extends ImageServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1268094928322577233L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StorageImage() {
        super(Configuration.STORAGE_IMAGE_PATH);
    }
    
}
