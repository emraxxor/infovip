package com.github.infovip.core.es.query;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.github.infovip.core.es.query.type.BeforeQuery;
import com.github.infovip.core.es.query.type.OnCreationComplete;
import com.github.infovip.core.es.query.type.Query;
import com.github.infovip.core.es.query.type.QuerySource;
import com.github.infovip.core.es.query.type.ResponseData;
import com.github.infovip.core.web.exceptions.UnsupportedTypeException;

/**
 * Simple query executor for simple queries
 * 
 * @author Attila Barna
 *
 */
public class DefaultQueryExecutor {

	
	private static Logger logger = Logger.getLogger(DefaultQueryExecutor.class);
	
    public static <RESPONSE , QUERY > 
    			RESPONSE generate(RESPONSE o, QUERY querySource) throws UnsupportedTypeException {
    	
    	if (!( o instanceof DefaultQueryResponse) ) {
    		throw new RuntimeException("Response must be an instance of DefaultQueryResponse");
    	}
    	
    	if (!( querySource instanceof DefaultQueryInterface) ) {
    		throw new RuntimeException("Response must be an instance of DefaultQueryResponse");
    	}
    	
        Method[] methods = o.getClass().getMethods();
        
        // init
        // pre - init
        for (Method method : methods) {
            if ( method.getAnnotation(QuerySource.class) != null) { 
            	try {
					method.invoke(o, querySource);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.error(e.getMessage(),e);
				}
            }
        }
        
        // before query
        for (Method method : methods) {
            if ( method.getAnnotation(BeforeQuery.class) != null) { 
            	try {
					method.invoke(o);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.error(e.getMessage(),e);
				}
            }
        }

        
        // after init
        for (Method method : methods) {
            if ( method.getAnnotation(Query.class) != null) { 
            	try {
					method.invoke(o);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.error(e.getMessage(),e);
				}
            }
        }

 
        // response
        for (Method method : methods) {
            if ( method.getAnnotation(ResponseData.class) != null) { 
            	try {
					method.invoke(o);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.error(e.getMessage(),e);
				}
            }
        }
        
        
        // onCreationComplete
        for (Method method : methods) {
            if ( method.getAnnotation(OnCreationComplete.class) != null) { 
            	try {
					method.invoke(o);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.error(e.getMessage(),e);
				}
            }
        }
    	
    	return o;
    }
	
}
