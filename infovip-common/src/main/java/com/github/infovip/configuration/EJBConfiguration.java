package com.github.infovip.configuration;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 
 * @author Attila Barna
 * @category infovip.common.configuration
 *
 */
public class EJBConfiguration {

    /**
     * Defines the name of the application
     */
    public static final String APP_NAME = "infovip-ear-1.0-SNAPSHOT";

    
    /**
     * Defines the names of available modules.
     * @author attila
     *
     */
    public enum EJB_MODULE implements Serializable {
    	
    	INFOVIP_USER("infovip-ejb-user-1.0-SNAPSHOT");
    	
        private String value;

        private EJB_MODULE(String val) {
            this.value = val;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    
    public static final String jndiLookupName(String mappedName,EJB_MODULE module) {
        return String.format("java:global/%s/%s/%s", APP_NAME, module.toString(), mappedName);
    }

    
    /**
     * Default lookup method
     * @param classz
     * @return
     */
    public static <T> T lookup(Class<T> classz,EJB_MODULE module) {
        try {
            Context c = new InitialContext();
            return (T) c.lookup(jndiLookupName(classz.getSimpleName(), module));
        } catch (NamingException ne) {
            Logger.getLogger("Lookup:").log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
