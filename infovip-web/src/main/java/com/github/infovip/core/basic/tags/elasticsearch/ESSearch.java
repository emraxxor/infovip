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

import static com.github.infovip.core.basic.jsp.Scope.scope;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
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
    private ElasticsearchRestTemplate template;

    private RestHighLevelClient restHighLevelClient;
    
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
        template =  applicationContext.getBean(ElasticsearchRestTemplate.class);
        restHighLevelClient = applicationContext.getBean(RestHighLevelClient.class);
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        jsonString = bodyContent.getString();
        if (entityClass != null) {
            try {
                Class c = Class.forName(entityClass);
                Query sq = new NativeSearchQuery(QueryBuilders.wrapperQuery(jsonString));
                SearchHits res = template.search( sq , c );
                
                List resultAsList = res.getSearchHits(); 
                pageContext.setAttribute(result, resultAsList, scope(scope));
                pageContext.getRequest().setAttribute(result, resultAsList);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ESSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (index != null && type != null) {
			try {
				SearchRequest searchRequest = new SearchRequest(index); 
				SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 				
				sourceBuilder.query( QueryBuilders.wrapperQuery(jsonString) ); 
				searchRequest.source(sourceBuilder);
				SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
				pageContext.setAttribute(result, response, scope(scope));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
