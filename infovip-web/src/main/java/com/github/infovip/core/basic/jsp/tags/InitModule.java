package com.github.infovip.core.basic.jsp.tags;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author attila
 */
public class InitModule extends SimpleTagSupport {

    private String moduleLocation;
    private String moduleQueryString;

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        
        if (request.getQueryString() != null) {
            if (request.getQueryString().indexOf('&') == -1) {
                if (request.getQueryString().equalsIgnoreCase(moduleQueryString)) {
                    try {
                        pageContext.include(moduleLocation, true);
                    } catch (ServletException | IOException ex) {
                        Logger.getLogger(InitModule.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (request.getQueryString().split("&")[0].equalsIgnoreCase(moduleQueryString)) {
                try {
                    pageContext.include(moduleLocation, true);
                } catch (ServletException | IOException ex) {
                    Logger.getLogger(InitModule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (moduleQueryString.equalsIgnoreCase("")) {
            try {
                pageContext.include(moduleLocation, true);
            } catch (ServletException ex) {
                Logger.getLogger(InitModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void setModuleLocation(String moduleLocation) {
        this.moduleLocation = moduleLocation;
    }

    public void setModuleQueryString(String moduleQueryString) {
        this.moduleQueryString = moduleQueryString;
    }

}
