package com.github.infovip.spring.components;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESDataElement;
import com.github.infovip.core.log.BaseLogDataElement;
import com.github.infovip.core.log.BaseLogElement;
import com.github.infovip.core.log.DefaultLogGenerator;
import com.github.infovip.core.log.LogGenerator;
import com.github.infovip.core.log.LogManager;

/**
 * 
 * @author Attila Barna
 *
 */
@Component
public class DefaultLogManager<D extends BaseLogDataElement, T extends BaseLogElement<D>> implements LogManager<T, WebApplicationContext, HttpServletRequest> {

    @Autowired
    private WebApplicationContext webAppContext;
    
    @Autowired
    private ESContainerInterface<ESDataElement<?>> esContainer;

    @Override
    public <K extends BaseLogDataElement> LogGenerator<K, HttpServletRequest> create(K element, HttpServletRequest request) {
	   return new DefaultLogGenerator<K, HttpServletRequest>(element, request);
    }
    
	@Override
	public void addLog(T log) {
		esContainer.add(log);
	}
	
	@Override
	public Object synch(T log) {
		return esContainer.executeSynchronusRequest(log);
	}

	@Override
	public WebApplicationContext context() {
		return webAppContext;
	}

}
