package com.github.infovip.core.web.sitemap;

import java.time.LocalDate;

import com.redfin.sitemapgenerator.WebSitemapUrl;

public class DefaultSiteMapEntry {

	private String documentTitle;
	private String documentContent;
	private WebSitemapUrl url;
	private LocalDate date;
	
	public DefaultSiteMapEntry(WebSitemapUrl url) {
		this.url = url;
	}
	
	public String getDocumentTitle() {
		return documentTitle;
	}
	
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	
	public String getDocumentContent() {
		return documentContent;
	}
	
	public void setDocumentContent(String documentContent) {
		this.documentContent = documentContent;
	}
	
	public WebSitemapUrl getUrl() {
		return url;
	}
	
	public void setUrl(WebSitemapUrl url) {
		this.url = url;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
