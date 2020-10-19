package com.github.infovip.core.basic.tags.sql;

import static com.github.infovip.core.Configuration.BEAN_SQL_ID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.github.infovip.core.basic.sql.SqlConnection;


public class SqlInit extends SimpleTagSupport {

    private String dburl;
    private String user;
    private String password;

    @Override
    public void doTag() throws JspException {
    	SqlConnection sqlcon = new SqlConnection();
    	sqlcon.init(dburl, user, password);
    	getJspContext().setAttribute( BEAN_SQL_ID  , sqlcon, PageContext.REQUEST_SCOPE);
    }

    public void setDburl(String dburl) {
        this.dburl = dburl;
    }

    public String getDburl() {
        return dburl;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
