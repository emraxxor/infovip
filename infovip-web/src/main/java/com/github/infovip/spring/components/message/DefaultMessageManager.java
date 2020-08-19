package com.github.infovip.spring.components.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.smtp.SmtpClient;
import com.github.infovip.web.application.message.ApplicationMessageManager;
import com.github.infovip.web.application.message.Message;

@Component
public class DefaultMessageManager implements ApplicationMessageManager {

	@Autowired
	private ESContainerInterface<ESExtendedDataElement<?>> esContainer;
	
	@Autowired
	private SmtpClient smtpClient;
	
	@Override
	public void addMessage(Message<?> message) {
		esContainer.executeThenGet(message);
		smtpClient.sendMessage(message);
	}

}
