package com.github.infovip.core.web.product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.github.infovip.core.Configuration;

/**
 * Servlet implementation class ProductImage
 */
@WebServlet("/ProductImage")
public class ProductImage extends ImageServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductImage() {
        super(Configuration.PRODUCT_IMAGE_PATH);
    }

}
