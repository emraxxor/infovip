package com.github.infovip.core.smtp;

import com.github.infovip.web.application.message.Message;

public interface SmtpClient {

	public void sendMessage(Message<?> message);
	
	public void sendHTML(String[] to, String subject,String html);
	
	public void sendPlain(String[] to, String subject,String html);
}
