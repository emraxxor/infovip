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
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.infovip.core.elasticsearch.DefaultElasticsearchTemplate;

/**
 * ESDelete removes a simply entity using ElasticsearchTemplate
 *
 * @author attila
 */
public class ESDelete extends BodyTagSupport {

    private Object entity;

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
     * Creates new instance of tag handler
     */
    public ESDelete() {
        super();
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    /**
     * Removes the given entity
     *
     * @throws JspException
     */
    private void deleteDocumentById() throws JspException {
        Class entityClass = entity.getClass();
        Annotation classAnnotation = entityClass.getAnnotation(Document.class);
        Document d = (Document) classAnnotation;
        Field[] fields = entityClass.getDeclaredFields();
        String documentId = null;
        for (Field f : fields) {
            if (f.getAnnotation(Id.class) != null) {
                try {
                    Method m = entityClass.getMethod(String.format("get%s", StringUtils.capitalize(f.getName())));
                    documentId = (String) m.invoke(entity);
                    break;
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(ESDelete.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (documentId != null) {
            template.delete(d.indexName(), d.type(), documentId);
            template.refresh(entityClass);
        } else {
            throw new JspException("Document cannot be deleted! The id of the document is not specified.");
        }
    }

    @Override
    public int doStartTag() throws JspException {
        applicationContext = WebApplicationContextUtils.findWebApplicationContext(pageContext.getServletContext());
        template = (ElasticsearchTemplate) applicationContext.getBean(ELASTICSEARCH_TEMPLATE_NAME, DefaultElasticsearchTemplate.class);
        deleteDocumentById();
        return SKIP_PAGE;
    }

}
