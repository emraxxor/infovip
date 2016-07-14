/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.tags.web;

import com.github.infovip.core.Configuration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author attila
 */
public class WebPage extends BodyTagSupport {

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        PageContext context = pageContext;
        try {
            try {
                context.include(Configuration.WEB_DIRECTORY + "/template/header.jsp", true);
            } catch (ServletException ex) {
                Logger.getLogger(WebPage.class.getName()).log(Level.SEVERE, null, ex);
            }

            JspWriter out = bodyContent.getEnclosingWriter();

            bodyContent.writeOut(out);

            try {
                context.include(Configuration.WEB_DIRECTORY + "/template/footer.jsp", true);
            } catch (ServletException ex) {
                Logger.getLogger(WebPage.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in WebApplication tag", ex);
        }

        bodyContent.clearBody();
        return SKIP_BODY;
    }

}
