package com.github.infovip.core.basic.tags.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class Free extends BodyTagSupport {

    private String var;

    @Override
    public int doStartTag() throws JspException {
        ResultSet rs = ((ResultSet) pageContext.getAttribute(var, PageContext.APPLICATION_SCOPE));
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Free.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return SKIP_BODY;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

}
