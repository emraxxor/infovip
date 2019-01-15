package com.github.infovip.core.web.sitemap;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import com.github.infovip.core.Configuration;
import com.redfin.sitemapgenerator.SitemapIndexGenerator;
import com.redfin.sitemapgenerator.SitemapIndexUrl;

public class DefaultSitemapIndexGenerator {

	private Configuration configuration;

	private Logger logger = Logger.getLogger(DefaultSitemapIndexGenerator.class);

	private SitemapIndexGenerator sitemap;
	
	private SiteMapIndexData siteMapIndexChildData;
	
	private SiteMapIndexData siteMapIndexRootData;
	
	private final String BASE_URL; 
	
	public DefaultSitemapIndexGenerator(Configuration configuration) {
		this.configuration = configuration;
		this.BASE_URL = configuration.getTemporaryConfig().getWebsiteAddress();
		
		try {
			sitemap = new SitemapIndexGenerator(BASE_URL);
			sitemap.addUrl(new SitemapIndexUrl( BASE_URL + "/sitemap-hu-main.xml"));
		} catch (MalformedURLException e) {
			logger.error(e);
		}
	}

	public SiteMapIndexData getSiteMapIndexChildData() {
		return siteMapIndexChildData;
	}
	
	public SiteMapIndexData getSiteMapIndexRootData() {
		return siteMapIndexRootData;
	}
	
	public DefaultSitemapIndexGenerator generate() {
		try {
			//for(SiteMapCategory c : siteMapIndexRootData.getCategories()) 
			//	sitemap.addUrl(new SitemapIndexUrl( BASE_URL + "/sitemap-" + c.getUrlPattern() + ".xml" ));

			for(SiteMapCategory c : siteMapIndexChildData.getCategories()) 
				sitemap.addUrl(new SitemapIndexUrl( BASE_URL + "/sitemap-" + c.getUrlPattern() + ".xml" ));
			
		} catch (MalformedURLException e) {
			logger.error(e);
		}
		return this;
	}
	
	
	@Override
	public String toString() {
		return sitemap.writeAsString();
	};

}