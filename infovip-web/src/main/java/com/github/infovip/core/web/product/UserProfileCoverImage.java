package com.github.infovip.core.web.product;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.github.infovip.core.Configuration;

/**
 * Servlet implementation class UserProfileCoverImage
 */
@WebServlet("/user/image/cover")
public class UserProfileCoverImage extends ImageServlet {
	private static final long serialVersionUID = 1L;
       
	
	private Logger logger = Logger.getLogger(UserProfileCoverImage.class);

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileCoverImage() {
        super(Configuration.USER_IMAGE_PATH);
    }
    
    @Override
    public void defaultImage(HttpServletResponse response) {
    	ServletOutputStream out = null;
    	try {
    		out = response.getOutputStream();
    		InputStream resource = getServletContext().getResourceAsStream("/WEB-INF/resources/img/default-cover.jpg");
    		response.setContentType("image/jpeg");  
			out.write(IOUtils.toByteArray(resource));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
    }

}
