package com.github.infovip.core.basic.tags.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.basic.sql.SqlConnection;

/**
 * <PreparedSelect var="object"><br>
 * SELECT * FROM t1 WHERE c1=? AND c2=?<br>
 * <PreparedValue type="int" value="5"><br>
 * <PreparedValue type="int" value="3"><br>
 * </PreparedBody><br>
 * <SqlNext var="object"><br>
 * value : <OutValue field="c1"><br>
 * </SqlNext></br>
 *
 * @author Barna Attila
 * @see PreparedBody
 * @see PreparedSelect
 * @see SqlNext
 *
 */
public class PreparedSelect extends BodyTagSupport {

    private ArrayList<SqlValue> values;
    private String var;

    public PreparedSelect() {
        values = new ArrayList<>();
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) ((SqlConnection) pageContext.getAttribute(Configuration.BEAN_SQL_ID, PageContext.APPLICATION_SCOPE))
                    .getConn().prepareStatement(bodyContent.getString());
        } catch (SQLException ex) {
            Logger.getLogger(PreparedSelect.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (values.isEmpty() == false) {
            int i = 1;
            for (SqlValue v : values) {
                try {
                    String val = v.getValue();
                    switch (v.getType()) {
                        case STRING:
                            pstmt.setString(i, val);
                            break;
                        case DOUBLE:
                            pstmt.setDouble(i, Double.valueOf(val));
                            break;
                        case FLOAT:
                            pstmt.setFloat(i, Float.valueOf(val));
                            break;
                        case INT:
                            pstmt.setInt(i, Integer.valueOf(val));
                            break;
                        default:
                            pstmt.setString(i, val);
                            break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PreparedSelect.class.getName()).log(Level.SEVERE, null, ex);
                }
                i++;
            }
        }

        try {
            pageContext.setAttribute(var, pstmt.executeQuery(), PageContext.APPLICATION_SCOPE);
        } catch (SQLException ex) {
            Logger.getLogger(PreparedSelect.class.getName()).log(Level.SEVERE, null, ex);
        }

        values.clear();
        return SKIP_BODY;
    }

    public void addValue(SqlValue e) {
        values.add(e);
    }
}
