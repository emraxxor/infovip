package com.github.infovip.core.web;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * URL santizier provides a way to handle and avoid XSS
 * @author attila
 *
 */
public class URLSanitizer {

	/**
	 * The standard and unique sanitizer
	 * @param v
	 */
	public static String standard(String v) {
		return Jsoup.clean(v,Whitelist.none()).replaceAll("\"","&quote;");
	}
	
}
