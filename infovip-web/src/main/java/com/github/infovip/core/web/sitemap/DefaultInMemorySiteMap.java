package com.github.infovip.core.web.sitemap;

import java.net.MalformedURLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.github.infovip.core.Configuration;
import com.redfin.sitemapgenerator.WebSitemapGenerator;

public class DefaultInMemorySiteMap<T extends DefaultSiteMapEntry> {
	

	private Configuration configuration;

	private Logger logger = Logger.getLogger(DefaultInMemorySiteMap.class);

	private WebSitemapGenerator sitemap;

	private final String BASE_URL; 

	public DefaultInMemorySiteMap(Configuration configuration) {
		this.configuration = configuration;
		this.BASE_URL = configuration.getTemporaryConfig().getWebsiteAddress();
		try {
			sitemap = new WebSitemapGenerator(configuration.getTemporaryConfig().getWebsiteAddress());
		} catch (MalformedURLException e) {
			logger.error(e);
		}
	}
	
	public void generateMain() throws Exception {
		throw new Exception("Not implemented yet.");
	}
	
	@Override
	public String toString() {
		return String.join("", sitemap.writeAsStrings() );
	}
	
	public void generate(List<T> data) {
		for(T o : data) {
			sitemap.addUrl(o.getUrl());
		}
	}


}
