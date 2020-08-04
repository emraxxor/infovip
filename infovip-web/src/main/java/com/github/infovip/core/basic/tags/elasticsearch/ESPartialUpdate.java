package com.github.infovip.core.basic.tags.elasticsearch;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class ESPartialUpdate extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5738415876773132318L;

	/**
	 * Default template
	 */
	private ElasticsearchRestTemplate template;
	
	private RestHighLevelClient highLevelClient;

	/**
	 * Application context
	 *
	 */
	private ApplicationContext applicationContext;

	/**
	 * The current entity
	 */
	private Object entity;
	
	private Map<String,Object> fieldsToUpdate;

	public ESPartialUpdate() {
		super();
	}
	

    @Override
    public int doStartTag() throws JspException {
        applicationContext = WebApplicationContextUtils.findWebApplicationContext(pageContext.getServletContext());
        template = applicationContext.getBean(ElasticsearchRestTemplate.class);
        highLevelClient = applicationContext.getBean(RestHighLevelClient.class);

        fieldsToUpdate = new HashMap<>();
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
    	// TODO Auto-generated method stub
    	return super.doAfterBody();
    }

    @Override
    public int doEndTag() throws JspException {
        try {
        	String id = null;
			Class entityClass = entity.getClass();
            Field[] fields = entityClass.getDeclaredFields();
            Annotation classAnnotation = entityClass.getAnnotation(Document.class);
            UpdateRequest updateRequest = new UpdateRequest();
            Document d = (Document) classAnnotation;
            updateRequest.index(d.indexName());
            XContentBuilder builder = XContentFactory.jsonBuilder();

            for (Field f : fields) {
                if (f.getAnnotation(Id.class) != null) {
                    try {
                        Method m = entityClass.getMethod(String.format("get%s", StringUtils.capitalize(f.getName())));
                        id = ((String) m.invoke(entity));
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(ESUpdate.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            for(String f : fieldsToUpdate.keySet()) {
                    try {
                        Method m = entityClass.getMethod(String.format("get%s", StringUtils.capitalize(f)));
                        builder
                                .startObject()
                                .field(f, fieldsToUpdate.get(f))
                                .endObject();
                    } catch (Exception ex) {
                        Logger.getLogger(ESUpdate.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
          
            updateRequest.id(id);
            updateRequest.doc(builder);
            updateRequest.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
            highLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            
        } catch (IOException ex) {
            Logger.getLogger(ESUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entity = null;
        }
        return EVAL_PAGE;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
    
    public Object getEntity() {
		return entity;
	}
    
    public void addField(String fieldName,Object value) {
    	fieldsToUpdate.put(fieldName, value);
    }
}
