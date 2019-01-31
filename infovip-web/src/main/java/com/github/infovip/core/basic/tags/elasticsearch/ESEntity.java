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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.infovip.core.elasticsearch.DefaultElasticsearchTemplate;

/**
 * Since the JSTL "foreach" tag cannot iterate the entities I created a simple
 * tag handler to manage entity beans.
 * <br/>
 * The ESEntity is the basis for management entities of the application.
 * ESEntity expects two parameters, one is the entity other is the name of the
 * variable to store the given entity. The scope of the variable can not be
 * changed, default scope which is used to store the variable is page scope.
 * <br/>
 * By default the child elements will always try to get the entity from the
 * parent object in order to use it for further operations. If this is not
 * possible the child elements will try to get the entity from the given scope.
 * If neither is possible, an exception will be thrown.
 *
 * <h2>Using entity manager</h2>
 * <h3>Iterating entity</h3>
 * <esentity:entity entity="${iterableEntityObject}" var="data">
 * <esentity:foreach current="data">
 * FieldName1 : <esentity:out field="fieldName1" /><br>
 * FieldName2 : <esentity:out field="fieldName2" /> <br>
 * </esentity:foreach>
 * </esentity:entity>
 * <p>
 *
 * <h3>Merging entity</h3>
 * <esentity:entity entity="${iterableEntityObject}" var="data">
 * <esentity:foreach current="data">
 * <esentity:merge field="fieldName" where="stringval" to="newvalue"/>
 * </esentity:foreach">
 * </esentity:entity>
 * <p>
 * <h3>Create entity</h3>
 * <esentity:create entity="${}">
 *
 * </esentity:create>
 *
 * @author attila
 */
public class ESEntity extends BodyTagSupport {

    /**
     * Default template
     */
    private ElasticsearchTemplate template;

    /**
     * The given entity or iterable object
     */
    private Object entity;

    /**
     * Name of the variable to store entity
     */
    private String var;

    /**
     * Application context
     *
     */
    ApplicationContext applicationContext;

    public ESEntity() {
        super();
    }

    @Override
    public int doStartTag() throws JspException {
        applicationContext = WebApplicationContextUtils.findWebApplicationContext(pageContext.getServletContext());
        template = (ElasticsearchTemplate) applicationContext.getBean(ELASTICSEARCH_TEMPLATE_NAME, DefaultElasticsearchTemplate.class);

        if (var != null) {
            pageContext.setAttribute(var, entity, PageContext.PAGE_SCOPE);
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
