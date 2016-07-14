package com.github.infovip.core.basic.tags.sql;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * <PreparedSelect var="object"><br>
 * SELECT * FROM t1 WHERE c1=? AND c2=?<br>
 * <PreparedValue value="5"><br>
 * <PreparedValue value="3"><br>
 * </PreparedBody><br>
 * <SqlNext var="object"><br>
 *  value: <OutValue field="c1"><br>
 * </SqlNext></br>
 *
 */
public class PreparedValue extends SimpleTagSupport {

    private String value;

    private String type;

    @Override
    public void doTag() throws JspException, IOException {
        if (getParent() instanceof PreparedBody) {
            PreparedBody pb = (PreparedBody) getParent();
            pb.addValue(new SqlValue(SQLTYPE.type(type), value));
        } else if (getParent() instanceof PreparedSelect) {
            PreparedSelect pb = (PreparedSelect) getParent();
            pb.addValue(new SqlValue(SQLTYPE.type(type), value));
        } else {
            try {
                throw new Exception("This class must be call of instace of PreparedBody or instance of PreparedSelect.");
            } catch (Exception ex) {
                Logger.getLogger(PreparedValue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
