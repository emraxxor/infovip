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

import static com.github.infovip.core.Configuration.ELASTICSEARCH_TEMPLATE_NAME;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.IterationTag.EVAL_BODY_AGAIN;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author attila
 */
public class ESEntity extends BodyTagSupport {

    private ElasticsearchTemplate template;

    private Object entity;

    private String var;

    ApplicationContext applicationContext;

    public ESEntity() {
        super();
    }

    @Override
    public int doStartTag() throws JspException {
        applicationContext = WebApplicationContextUtils.findWebApplicationContext(pageContext.getServletContext());
        template = applicationContext.getBean(ELASTICSEARCH_TEMPLATE_NAME, ElasticsearchTemplate.class);

        if (var != null) {
            pageContext.setAttribute(var, entity);
        }

        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            JspWriter out = null;
            if (bodyContent == null) {
                out = pageContext.getOut();
            } else {
                out = bodyContent.getEnclosingWriter();
            }
            out.println(bodyContent.getString());
        } catch (IOException ex) {
            Logger.getLogger(ESEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        pageContext.removeAttribute(var);
        return EVAL_PAGE;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }

    public ElasticsearchTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ElasticsearchTemplate template) {
        this.template = template;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

}
