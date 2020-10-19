/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.core.basic.tags.sql;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.basic.sql.SqlConnection;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author attila
 */
public class DestroyConnection extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException {
        ((SqlConnection) getJspContext().getAttribute(Configuration.BEAN_SQL_ID, PageContext.REQUEST_SCOPE)).destroyConnect();
        getJspContext().removeAttribute(Configuration.BEAN_SQL_ID);
    }

}
