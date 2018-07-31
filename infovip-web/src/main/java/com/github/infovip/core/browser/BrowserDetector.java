package com.github.infovip.core.browser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class BrowserDetector {

	private static Pattern pattern = Pattern.compile(".*(bot|crawler|archiver|transcoder|spider|uptime|validator|fetcher).*",Pattern.CASE_INSENSITIVE);

	public static boolean isBot(String header) {
		if ( header != null ) {
			Matcher matcher = pattern.matcher(header);
			return matcher.matches();
		} 
		return false;
	}

	public static boolean isBot(HttpServletRequest request) {
		if ( request.getHeader("User-Agent") != null) {
			Matcher matcher = pattern.matcher(request.getHeader("User-Agent"));
			return matcher.matches();
		} 
		return false;
	}

}
