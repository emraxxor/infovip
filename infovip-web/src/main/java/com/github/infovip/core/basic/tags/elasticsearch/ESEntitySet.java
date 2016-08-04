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

import com.github.infovip.core.basic.tags.elasticsearch.exceptions.ExpectedJSPParentClassException;
import com.github.infovip.core.elasticsearch.DefaultDateFormatter;
import com.github.infovip.core.elasticsearch.DefaultDateFormatter.PATTERN;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

/**
 * The ESEntitySet allows to modify the properties of an existing entity.
 * Changing a property has no effect on the entity.
 *
 * @author attila
 */
public class ESEntitySet extends BodyTagSupport {

    /**
     * Field which will be modified
     */
    private String field;

    /**
     * Value of the field
     */
    private Object value;

    /**
     * Type of the current field
     */
    private String type;

    /**
     * Type of the date if the field type is date
     */
    private String dateType;

    /**
     * If the field type is date then it is possible to use unique pattern for
     * converting it to DateTime - object
     */
    private String pattern;

    /**
     * The entity on which the changes are performed.
     */
    private Object entity;

    /**
     * Creates new instance of tag handler
     */
    public ESEntitySet() {
        super();
    }

    @Override
    public int doStartTag() throws JspException {
        if (entity == null) {
            if (getParent() instanceof ESEntityCreate) {
                entity = ((ESEntityCreate) getParent()).getEntity();
            } else {
                try {
                    throw new ExpectedJSPParentClassException(String.format("%s only can be used if the parent class is %s", this.getClass().getName(), ESEntityCreate.class.getName()));
                } catch (ExpectedJSPParentClassException ex) {
                    Logger.getLogger(ESEntitySet.class.getName()).log(Level.SEVERE, null, ex);
                    return SKIP_PAGE;
                }
            }
        }

        try {
            ESEntityFieldType etype = ESEntityFieldType.value(type);
            Object fieldValue;
            Class paramClass = null;
            switch (etype) {
                case DATE:
                    paramClass = Date.class;
                    if (dateType != null) {
                        PATTERN p = PATTERN.typeOf(dateType);
                        fieldValue = DefaultDateFormatter.parse(String.valueOf(value), p).toDate();
                        break;
                    } else if (pattern != null) {
                        fieldValue = DefaultDateFormatter.parse(String.valueOf(value), pattern).toDate();
                        break;
                    } else {
                        fieldValue = DefaultDateFormatter.parse(String.valueOf(value)).toDate();
                        break;
                    }
                case DOUBLE:
                    paramClass = Double.class;
                    fieldValue = Double.valueOf(String.valueOf(value));
                    break;
                case INTEGER:
                    paramClass = Integer.class;
                    fieldValue = Integer.valueOf(String.valueOf(value));
                    break;
                case FLOAT:
                    paramClass = Float.class;
                    fieldValue = Float.valueOf(String.valueOf(value));
                    break;
                case STRING:
                    paramClass = String.class;
                    fieldValue = String.valueOf(value);
                    break;
                case OBJECT:
                    paramClass = value.getClass();
                    fieldValue = value;
                    break;
                case LONG:
                    paramClass = Long.class;
                    fieldValue = Long.valueOf(String.valueOf(value));
                    break;
                default:
                    fieldValue = String.valueOf(value);
                    break;
            }

            Method m = entity.getClass().getMethod(String.format("set%s", StringUtils.capitalize(field)), paramClass);
            m.invoke(entity, fieldValue);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ParseException ex) {
            Logger.getLogger(ESEntitySet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        entity = null;
        return super.doEndTag();
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getType() {
        return type;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

}
