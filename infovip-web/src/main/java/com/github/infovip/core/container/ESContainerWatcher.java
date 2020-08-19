package com.github.infovip.core.container;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
/**
 * 
 * * @author Attila Barna
 * @category infovip.core.data.manager
 *
 */
@Component
public class ESContainerWatcher extends Thread {

	/**
	 * Default logger for the current component
	 */
	private Logger logger = Logger.getLogger(ESContainerWatcher.class);

	private boolean isRunning = true;
	
	@Autowired
	private ESContainer<ESExtendedDataElement<?>> esContainer;
	
	
	public ESContainerWatcher() {
	}
	
	public void postConstruct() {
		start();
	}
	
	public void preDestroy() {
		isRunning = false;
	}
	
	
	@Override
	public void run() {
		logger.info("Running watcher...");
		while(isRunning) {
			if ( esContainer.queueSize() > 0 ) {
				esContainer.flush();
			}
			
			try {
				Thread.sleep(TimeUnit.MILLISECONDS.convert(10, TimeUnit.MINUTES));
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
		logger.info("Destroying watcher...");
	}
		
}
