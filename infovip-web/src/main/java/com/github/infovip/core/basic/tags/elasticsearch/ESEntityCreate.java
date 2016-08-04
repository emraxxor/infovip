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
import static com.github.infovip.core.basic.jsp.Scope.scope;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * ESEntityCreate actually creates a new document then the document will be
 * added as an attribute to the current context. Scope for the context can also
 * be specified.
 *
 * @author attila
 */
public class ESEntityCreate extends BodyTagSupport {

    /**
     * Entity that will be created
     */
    private String entityClass;

    /**
     * Default template
     */
    private ElasticsearchTemplate template;

    /**
     * Application context
     *
     */
    ApplicationContext applicationContext;

    /**
     * Name of the variable to store result
     */
    private String result;

    /**
     * Sets the scope of the result
     */
    private String scope;

    /**
     * Current entity
     */
    private Object entity;

    /**
     * IndexQuery
     */
    private IndexQuery indexQuery;

    public ESEntityCreate() {
        super();
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            applicationContext = WebApplicationContextUtils.findWebApplicationContext(pageContext.getServletContext());
            template = applicationContext.getBean(ELASTICSEARCH_TEMPLATE_NAME, ElasticsearchTemplate.class);
            entity = Class.forName(entityClass).newInstance();
            indexQuery = new IndexQuery();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ESEntityCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        indexQuery.setObject(entity);
        template.index(indexQuery);
        template.refresh(entity.getClass());
        if (result != null) {
            pageContext.setAttribute(result, entity, scope(scope));
        }
        return SKIP_BODY;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public Object getEntity() {
        return entity;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
