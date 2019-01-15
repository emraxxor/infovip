package com.github.infovip.core.smtp;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.config.deprecated.TemporaryConfig;
import com.github.infovip.web.application.message.BaseMessage;
import com.github.infovip.web.application.message.Message;

public class DefaultSMTPClient implements SmtpClient {

	private Configuration configuration;
	
	private TemporaryConfig temporaryConfig;
	
	private Logger logger = Logger.getLogger(DefaultSMTPClient.class);
	
	public DefaultSMTPClient() {
	}
	
	
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
		try {
			HtmlEmail email = new HtmlEmail();
			email.setCharset(org.apache.commons.mail.EmailConstants.UTF_8);
			email.setHostName(temporaryConfig.getSmtp().getHostname());
			email.setSmtpPort(temporaryConfig.getSmtp().getPort());
			email.setAuthenticator(new DefaultAuthenticator(temporaryConfig.getSmtp().getUsername(), temporaryConfig.getSmtp().getPassword()));
			email.setSSLOnConnect(temporaryConfig.getSmtp().isSsl());
			email.setFrom(temporaryConfig.getSmtp().getFrom());
			email.setSSLCheckServerIdentity(temporaryConfig.getSmtp().isForceSSL());
			email.setStartTLSRequired(temporaryConfig.getSmtp().isUseStartTLS());
			email.setSubject(subject);
			email.setHtmlMsg(html);
			email.setTextMsg("Your email client does not support HTML messages");
			for(String o : to  ) 
				email.addTo(o);
			
			email.send();
		} catch (EmailException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	
	
	@Override
	public void sendPlain(String[] to, String subject, String html) {
		try {
			Email email = new HtmlEmail();
			email.setCharset(org.apache.commons.mail.EmailConstants.UTF_8);
			email.setHostName(temporaryConfig.getSmtp().getHostname());
			email.setSmtpPort(temporaryConfig.getSmtp().getPort());
			email.setAuthenticator(new DefaultAuthenticator(temporaryConfig.getSmtp().getUsername(), temporaryConfig.getSmtp().getPassword()));
			email.setSSLOnConnect(temporaryConfig.getSmtp().isSsl());
			email.setFrom(temporaryConfig.getSmtp().getFrom());
			email.setSSLCheckServerIdentity(temporaryConfig.getSmtp().isForceSSL());
			email.setStartTLSRequired(temporaryConfig.getSmtp().isUseStartTLS());
			email.setSubject(subject);
			email.setMsg(html);
			
			for(String o : to  ) 
				email.addTo(o);
			
			email.send();
		} catch (EmailException e) {
			logger.error(e.getMessage(),e);
		}
	}


	
	@Override
	public void sendMessage(Message<?> message) {
		BaseMessage bm = message.data();
		sendHTML(new String[] { bm.getTo() }, bm.getTitle(), bm.getMessage());
	}
}
