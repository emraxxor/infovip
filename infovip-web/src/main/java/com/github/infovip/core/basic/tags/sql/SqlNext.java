package com.github.infovip.core.basic.tags.sql;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class SqlNext extends BodyTagSupport {

    private String var;

    private boolean sqlResult;

    private ResultSet rs;

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            setHasRow(true);
            rs = (ResultSet) pageContext.getAttribute(var, PageContext.APPLICATION_SCOPE);
            JspWriter out = null;

            if (bodyContent == null) {
                out = pageContext.getOut();
            } else {
                out = bodyContent.getEnclosingWriter();
            }

            if (rs != null && rs.next() == true) {
                return EVAL_BODY_AGAIN;
            } else {
                setHasRow(false);
                rs.close();
                out.println(bodyContent.getString());
                return SKIP_PAGE;
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(SqlNext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SKIP_PAGE;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public void setHasRow(boolean sqlResult) {
        this.sqlResult = sqlResult;
    }

    public boolean hasRow() {
        return sqlResult;
    }

    public ResultSet getRs() {
        return rs;
    }

}
