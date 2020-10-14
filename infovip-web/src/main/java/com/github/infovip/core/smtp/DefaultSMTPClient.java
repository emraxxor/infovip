package com.github.infovip.core.smtp;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.config.deprecated.TemporaryConfig;
import com.github.infovip.web.application.message.BaseMessage;
import com.github.infovip.web.application.message.Message;

/**
 * 
 * @author Attila Barna
 *
 */

@Component
public class DefaultSMTPClient implements SmtpClient {

	private Configuration configuration;
	
	private TemporaryConfig temporaryConfig;
	
	private Logger logger = Logger.getLogger(DefaultSMTPClient.class);
	
	@Autowired
	public DefaultSMTPClient(Configuration c) {
		this.configuration = c;
	}
	
	@PostConstruct
	public void postInit() {
		this.temporaryConfig = this.configuration.getTemporaryConfig();
	}
	
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
	
	@Override
	public void sendHTML(String[] to, String subject, String html) {
		
		if ( !Configuration.PRODUCT_VERSION ) 
			return;
	
		DefaultEmailThread
		.create(temporaryConfig)
		.addTo(to)
		.setHtmlMsg(html)
		.setSubject(subject)
		.start();

	}
	
	
	
	@Override
	public void sendPlain(String[] to, String subject, String html) {
		
		if ( !Configuration.PRODUCT_VERSION ) 
			return;
	
		DefaultEmailThread
		.create(temporaryConfig)
		.addTo(to)
		.setHtmlMsg(html)
		.setSubject(subject)
		.start();
		
	}


	
	@Override
	public void sendMessage(Message<?> message) {
		BaseMessage bm = message.data();
		sendHTML(new String[] { bm.getTo() }, bm.getTitle(), bm.getMessage());
	}
}
