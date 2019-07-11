package com.github.infovip.spring.config;

import java.util.concurrent.ExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Application Lifecycle Listener implementation class DefaultServletListener
 * @author Attila Barna
 */
@Configuration
public class DefaultServletListener implements ServletContextListener {

    protected static final Logger logger = Logger.getLogger(DefaultServletListener.class);
    
    
    @Autowired
    private ExecutorService executorService;

    
    /**
     * Default constructor. 
     */
    public DefaultServletListener() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	executorService.shutdown();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    }
	
}
