package com.github.infovip.core.basic.tags.sql;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class OutValue extends BodyTagSupport {

    private String field;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        ResultSet rs = ((SqlNext) getParent()).getRs();
        SqlNext p = (SqlNext) getParent();

        if (!p.hasRow()) {
            return SKIP_BODY;
        }

        if (rs != null) {
            try {
                if (rs.isBeforeFirst() == false) {
                    if (rs.getString(field) != null) {
                        out.println(rs.getString(field));
                    } else {
                        out.println(String.format("Field : %s doesn't exists.", field));
                    }
                }
            } catch (NumberFormatException | SQLException | IOException e) {

            }
        }
        return SKIP_BODY;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
