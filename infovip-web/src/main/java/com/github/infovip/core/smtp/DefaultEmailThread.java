package com.github.infovip.core.smtp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.github.infovip.core.config.deprecated.TemporaryConfig;


/**
 * 
 * @author Attila Barna
 *
 */
public class DefaultEmailThread extends Thread {

	private HtmlEmail email;
	
	private final TemporaryConfig temporaryConfig;
	
	private String htmlMsg;

	private List<String> to = new ArrayList<>();
	
	private List<String> bccto = new ArrayList<>();
	
	private Logger logger = Logger.getLogger(DefaultEmailThread.class);

	public DefaultEmailThread(TemporaryConfig tconfig) {
		temporaryConfig = tconfig;
		
		email = new HtmlEmail();
		email.setCharset(org.apache.commons.mail.EmailConstants.UTF_8);
		email.setHostName(temporaryConfig.getSmtp().getHostname());
		email.setSmtpPort(temporaryConfig.getSmtp().getPort());
		email.setAuthenticator(new DefaultAuthenticator(temporaryConfig.getSmtp().getUsername(), temporaryConfig.getSmtp().getPassword()));
		email.setSSLOnConnect(temporaryConfig.getSmtp().isSsl());
		email.setSSLCheckServerIdentity(temporaryConfig.getSmtp().isForceSSL());
		email.setStartTLSRequired(temporaryConfig.getSmtp().isUseStartTLS());
	}
	
	public static DefaultEmailThread create(TemporaryConfig c) {
		return new DefaultEmailThread(c);
	}
	
	public DefaultEmailThread setSubject(String subject) {
		email.setSubject(subject);
		return this;
	}
	
	public DefaultEmailThread setHtmlMsg(String msg) {
		this.htmlMsg = msg;
		return this;
	}
	
	public DefaultEmailThread addBcc(String bcc) {
		bccto.add(bcc);
		return this;
	}
	
	public DefaultEmailThread addBcc(String[] bcc) {
		Arrays.asList(bcc).forEach(this.bccto::add);
		return this;
	}
	
	public DefaultEmailThread addTo(String to) {
		this.to.add(to);
		return this;
	}
	
	public DefaultEmailThread addTo(String[] to) {
		Arrays.asList(to).forEach( e -> this.to.add(e) );
		return this;
	}

	
	@Override
	public void run() {
		try {
			email.setFrom(temporaryConfig.getSmtp().getFrom(), temporaryConfig.getSmtp().getFromName());
			email.setTextMsg("Your email client does not support HTML messages");
			email.setHtmlMsg(htmlMsg);
			
			for(String e : to ) 
				email.addTo(e);
			
			for(String e : bccto)
				email.addBcc(e);


			logger.info(  "[MAIL][SEND] TO :  " + StringUtils.join(email.getToAddresses(),",") + "\n"
						+ "				SUBJECT: " + email.getSubject()  + "\n"
						+ "				BCC:"	+ StringUtils.join(email.getBccAddresses(),",")	+ "\n"		
			);
			
			email.send();
			
		} catch (EmailException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
