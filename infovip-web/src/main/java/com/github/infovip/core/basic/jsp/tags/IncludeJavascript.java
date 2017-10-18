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
package com.github.infovip.core.basic.jsp.tags;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.infovip.core.Container;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.js.JavaScript;

/**
 * Actually, it includes the given src as a JavaScript. At this time only type
 * is supported : text/javascript
 *
 * If the type of the JavaScript is not set then it is assigned with the default
 * value : TEXT_JAVASCRIPT
 *
 * Required arguments: - src : Location of the javascript
 *
 * @author attila
 */
public class IncludeJavascript extends BodyTagSupport {

    private String type;
    private String src;

    @Override
    public int doStartTag() throws JspException {
        try {
            ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
            Container con = context.getBean(Container.class);
            type = type == null ? JavaScript.ScriptType.TEXT_JAVASCRIPT.toString() : type;
            con.getJavascriptManager().addElement(new JavaScript(src, type));
        } catch (UnsupportedTypeException | IllegalStateException | BeansException ex) {
            Logger.getLogger(IncludeJavascript.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SKIP_BODY;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSrc(String src) {
        this.src = src;
    }

}
