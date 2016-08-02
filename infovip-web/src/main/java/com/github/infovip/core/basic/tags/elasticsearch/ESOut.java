/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.core.basic.tags.elasticsearch;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author attila
 */
public class ESOut extends BodyTagSupport {

    private String field;

    private Object entity;

    private static final Logger LOG = Logger.getLogger(ESOut.class.getName());

    @Override
    public int doStartTag() throws JspException {
        if (getParent() instanceof ESEntityForeach && entity == null) {
            entity = ((ESEntityForeach) getParent()).getEntityObject();
        }

        JspWriter out = pageContext.getOut();
        try {
            if (entity != null) {
                Method method = entity.getClass().getMethod(String.format("get%s", StringUtils.capitalize(field)));
                Object val = method.invoke(entity);
                out.println(val);
            }
        } catch (NoSuchMethodException | SecurityException ex) {
            LOG.log(Level.SEVERE, null, ex);

        } catch (IllegalArgumentException | IOException | IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        entity = null;
        return super.doEndTag(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

}
