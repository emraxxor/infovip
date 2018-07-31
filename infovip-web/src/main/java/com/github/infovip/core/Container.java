package com.github.infovip.core;

import java.io.Serializable;

import com.github.infovip.core.web.CSSManager;
import com.github.infovip.core.web.JavascriptManager;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;

/**
 *
 * @author Attila Barna
 * @category infovip.core.framework.container
 */
public class Container implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8330455004613377285L;

	private final JavascriptManager javascriptManager;

    private final CSSManager cssManager;

    private String pageTitle;
    
    public Container(JavascriptManager jsManager, CSSManager cssManager) throws UnsupportedTypeException {
        this.javascriptManager = jsManager;
        this.cssManager = cssManager;
        this.pageTitle = "";
    }

    public JavascriptManager getJavascriptManager() {
        return javascriptManager;
    }

    public CSSManager getCssManager() {
        return cssManager;
    }
    
    public String getPageTitle() {
		return pageTitle;
	}
    
    public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
}
