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
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.infovip.core.elasticsearch.DefaultElasticsearchTemplate;

/**
 *
 * ESUpdate actually updates an existing entity. Actually it is a workaround
 * because the documents in Elasticsearch are always immutable and they cannot
 * be replaced directly. <br/>
 * If it is required to update an existing document, first it needs to be
 * reindexed it. Existing document can by changed by using Index API as well.
 * Elasticsearch deletes the old document automatically and add a new document
 * internally.
 *
 * ESUpdate only updates a single document, if you need to update more than one
 * document then you need to use the bulk update api.
 *
 * @todo esentity:bulkupdate
 *
 *
 * @author attila
 */
public class ESUpdate extends BodyTagSupport {

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
     * The current entity
     */
    private Object entity;

    /**
     * Creates new instance of tag handler
     */
    public ESUpdate() {
        super();
    }

    @Override
    public int doStartTag() throws JspException {
        applicationContext = WebApplicationContextUtils.findWebApplicationContext(pageContext.getServletContext());
        template = (ElasticsearchTemplate) applicationContext.getBean(ELASTICSEARCH_TEMPLATE_NAME, DefaultElasticsearchTemplate.class);
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            Class entityClass = entity.getClass();
            Field[] fields = entityClass.getDeclaredFields();
            Annotation classAnnotation = entityClass.getAnnotation(Document.class);
            IndexRequest indexRequest = new IndexRequest();

            Document d = (Document) classAnnotation;
            indexRequest.index(d.indexName());
            indexRequest.type(d.type());

            // updating the whole document
            for (Field f : fields) {
                if (f.getAnnotation(Id.class) != null) {
                    try {
                        Method m = entityClass.getMethod(String.format("get%s", StringUtils.capitalize(f.getName())));
                        indexRequest.id((String) m.invoke(entity));
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(ESUpdate.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            template.index(new IndexQueryBuilder()
                    .withId(indexRequest.id())
                    .withIndexName(indexRequest.index())
                    .withObject(entity)
                    .withType(indexRequest.type())
                    .build()
            );
            template.refresh(entityClass);
        } catch (Exception ex) {
            Logger.getLogger(ESUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entity = null;
        }
        return EVAL_PAGE;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
