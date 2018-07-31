package com.github.infovip.core.web.product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.security.BasicSecureFunctions;

/**
 * Servlet implementation class ProductImage
 */
@WebServlet("/ProductImage")
public class ProductImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Logger logger = Logger.getLogger(ProductImage.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductImage() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (BasicSecureFunctions.directoryTraversalInputCheckStartsWith(request.getQueryString())) {
        	ServletOutputStream out = null;
        	try {
				FileInputStream fin = new FileInputStream(Configuration.PRODUCT_IMAGE_PATH + '/' +  request.getQueryString() );  
				response.setContentType("image/png");  
				out = response.getOutputStream();
				out.write(IOUtils.toByteArray(fin));
				out.flush();
				out.close();  
			} catch (Exception e) {
				if ( out != null ) {
					noticeUser(response,out);
				} else {
					noticeUser(response);
				}
			} finally {
				if ( out != null ) {
					out.close();
				}
			}
        } else {
        	noticeUser(response);
        } 
	} 
	
	private void noticeUser(HttpServletResponse response) throws IOException {
			response.setContentType("text/html;charset=UTF-8");
			try (PrintWriter out = response.getWriter()) {
				out.println("Oops... Something went wrong :(");
			} catch(Exception e) {
				logger.error(e.getMessage(),e);
			}
	}
	
	private void noticeUser(HttpServletResponse response, ServletOutputStream out) throws IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			out.println("Oops... Something went wrong :(");
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
