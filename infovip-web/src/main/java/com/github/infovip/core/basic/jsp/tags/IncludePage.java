package com.github.infovip.core.basic.jsp.tags;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.github.infovip.core.Configuration;

/**
 *
 * @author attila
 */
public class IncludePage extends SimpleTagSupport {

    private String type;
    private String file;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        try {
            pageContext.include(String.format("%s/%s/%s.jsp", Configuration.WEB_DIRECTORY, type, file), true);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(IncludePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
