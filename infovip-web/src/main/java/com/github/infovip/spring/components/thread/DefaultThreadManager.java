package com.github.infovip.spring.components.thread;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.core.data.type.ApplicationThread;
import com.github.infovip.core.data.type.ApplicationThreadManager;

/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class DefaultThreadManager implements ApplicationThreadManager<WebApplicationContext> {

    @Autowired
    private WebApplicationContext webAppContext;

	
	private final Logger logger = Logger.getLogger(DefaultThreadManager.class);
	
	/**
	 * Current threads holder
	 */
	private final List<ApplicationThread<WebApplicationContext>> threads;

	
	public DefaultThreadManager() {
		this.threads = Collections.synchronizedList(new LinkedList<ApplicationThread<WebApplicationContext>>());
	}
	
	
	@PostConstruct
	public void postConstruct() {
	}
	

	public synchronized void addThread(ApplicationThread<WebApplicationContext> thread) {
		thread.setApplicationThreadManager(this);
		thread.setContext(webAppContext);
		thread.postConstruct();
		thread.beforeStart();
		thread.start();
		threads.add(thread);
	}
	
	public synchronized void removeThread(ApplicationThread<WebApplicationContext> thread) {
		thread.beforeRemove();
		this.threads.remove(thread);
	}
	
	public synchronized int size() {
		return this.threads.size();
	}
	
	
	public synchronized boolean isThereFreePlace() {
		return size() < 20;
	}
	
	@Override
	public synchronized List<ApplicationThread<WebApplicationContext>> threads() {
		return threads;
	}
}
