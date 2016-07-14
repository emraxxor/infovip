package com.github.infovip.core.basic.tags.sql;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.basic.sql.SqlConnection;
import java.sql.ResultSet;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class SqlSelect extends BodyTagSupport {

    private String var;

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        SqlConnection sqlcon = (SqlConnection) pageContext.getAttribute(Configuration.BEAN_SQL_ID, PageContext.APPLICATION_SCOPE);
        ResultSet rs = sqlcon.selectQuery(bodyContent.getString());
        pageContext.setAttribute(var, rs, PageContext.APPLICATION_SCOPE);
        return EVAL_PAGE;
    }

    /**
     * @return the var
     */
    public String getVar() {
        return var;
    }

    /**
     * @param var the var to set
     */
    public void setVar(String var) {
        this.var = var;
    }
}
