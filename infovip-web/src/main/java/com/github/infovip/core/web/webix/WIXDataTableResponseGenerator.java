package com.github.infovip.core.web.webix;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.infovip.core.web.exceptions.UnsupportedTypeException;
import com.github.infovip.core.web.webix.markers.datatable.WIXDataSource;
import com.github.infovip.core.web.webix.markers.datatable.WIXDataTable;

public class WIXDataTableResponseGenerator {

	public WIXDataTableResponseGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param o
	 * @param source
	 * @return
	 * @throws UnsupportedTypeException
	 */
    public static <T,V,K,J> T generate(T o,V source, Object... params) throws UnsupportedTypeException {
        if (!(o instanceof DataTableResponse)) {
            throw new UnsupportedTypeException("It can only be used by object that implements DataTableResponse.");
        }

        if ( o instanceof DataTableResponse ) {
        	((DataTableResponse) o).setParams(Arrays.asList(params));
        }
        
        Method[] methods = o.getClass().getMethods();
       
        // pre - init
        for (Method method : methods) {
            if ( method.getAnnotation(WIXDataSource.class) != null) { 
            	try {
					method.invoke(o, source);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					Logger.getLogger(WIXDataTableResponseGenerator.class.getName()).log(Level.SEVERE, null, e);
				}
            }
        }
        
        // data logic
        for (Method method : methods ) {
        	if (method.getAnnotation(WIXDataTable.class) != null) {
                try {
                    method.invoke(o);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(WIXDataTableResponseGenerator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return o;
    }

}
