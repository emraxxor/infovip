package com.github.infovip.core.smtp;

public interface SmtpClient {

	public void sendHTML(String[] to, String subject,String html);
	
	public void sendPlain(String[] to, String subject,String html);
}
