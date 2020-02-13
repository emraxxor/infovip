package com.github.infovip.core.web.product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.github.infovip.core.security.BasicSecureFunctions;

public class ImageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7606163831886123497L;

	private Logger logger = Logger.getLogger(ImageServlet.class);
	
	protected String imageDirectory;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet(String imageDirectory) {
        super();
        this.imageDirectory = imageDirectory;
    }
    
    public void defaultImage(HttpServletResponse response) {
    	ServletOutputStream out = null;
    	try {
    		out = response.getOutputStream();
    		InputStream resource = getServletContext().getResourceAsStream("/WEB-INF/resources/img/default-gift-min.jpeg");
    		response.setContentType("image/jpeg");  
			out.write(IOUtils.toByteArray(resource));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		
        if (request.getQueryString() != null && BasicSecureFunctions.directoryTraversalInputCheckStartsWith(request.getQueryString())) {
        	try {
				FileInputStream fin = new FileInputStream(this.imageDirectory + '/' +  request.getQueryString() );  
				response.setContentType("image/jpg");  
				out.write(IOUtils.toByteArray(fin));
			} catch (Exception e) {
				defaultImage(response);
			} finally {
				if ( out != null  ) {
					out.flush();
					out.close();
				}
			}
        } else {
        	noticeUser(response,out);
        	out.flush();
			out.close();
        } 
	} 
	
	private void noticeUser(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("Oops... Something went wrong :(");
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
		} finally {}
	}
	
	private void noticeUser(HttpServletResponse response, ServletOutputStream out) throws IOException {
		if ( out == null ) 
			out = response.getOutputStream();

		try {
			response.setContentType("text/html;charset=UTF-8");
			out.println("Oops... Something went wrong :(");
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
		} finally {}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
