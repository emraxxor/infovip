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

import com.github.infovip.core.Configuration;
import com.github.infovip.core.elasticsearch.DefaultElasticsearchTemplate;

import static com.github.infovip.core.Configuration.ELASTICSEARCH_TEMPLATE_NAME;
import static com.github.infovip.core.basic.jsp.Scope.scope;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import static javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_BUFFERED;
import javax.servlet.jsp.tagext.BodyTagSupport;
import static javax.servlet.jsp.tagext.Tag.EVAL_PAGE;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * ESSearch allows to use native json object as a search query.
 * It actually just simple wraps the json and the result will be mapped as
 * a repository.
 *
 * @author attila
 */
public class ESSearch extends BodyTagSupport {

    /**
     * Application context
     */
    private WebApplicationContext applicationContext;

    /**
     * Default template
     */
    private ElasticsearchTemplate template;

    /**
     * The client
     */
    private Client client;

    /**
     * Parsed json string
     */
    private String jsonString;

    /**
     * Name of the variable to store the result
     */
    private String result;

    /**
     * Scope of the result
     */
    private String scope;

    /**
     * Using this index for searching
     */
    private String index;

    /**
     * Using this type for searching
     */
    private String type;

    /**
     *
     */
    private String entityClass;

    /**
     * Creates new instance of tag handler
     */
    public ESSearch() {
        super();
    }

    @Override
    public int doStartTag() throws JspException {
        applicationContext = WebApplicationContextUtils.findWebApplicationContext(pageContext.getServletContext());
        template = (ElasticsearchTemplate) applicationContext.getBean(ELASTICSEARCH_TEMPLATE_NAME, DefaultElasticsearchTemplate.class);
        client = template.getClient();
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        jsonString = bodyContent.getString();
        if (entityClass != null) {
            try {
                Class c = Class.forName(entityClass);
                SearchQuery sq = new NativeSearchQuery(QueryBuilders.wrapperQuery(jsonString));
                List res = template.queryForList(sq, c);
                pageContext.setAttribute(result, res, scope(scope));
                pageContext.getRequest().setAttribute(result, res);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ESSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (index != null && type != null) {
            SearchResponse response = client.prepareSearch(index).setTypes(type).setQuery(QueryBuilders.wrapperQuery(jsonString)).execute().actionGet();
            pageContext.setAttribute(result, response, scope(scope));
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        entityClass = null;
        index = null;
        type = null;
        return EVAL_PAGE;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

}
