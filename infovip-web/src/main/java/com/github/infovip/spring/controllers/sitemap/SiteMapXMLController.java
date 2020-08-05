package com.github.infovip.spring.controllers.sitemap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.core.Configuration;
import com.github.infovip.core.elasticsearch.ESConnection;
import com.github.infovip.core.web.sitemap.DefaultSitemapIndexGenerator;
import com.github.infovip.core.web.sitemap.SiteMapIndexView;

@Controller
@RequestMapping("/sitemap*")
public class SiteMapXMLController<T> {
	
	@Autowired
	private Configuration configuration;
	
	private Logger logger = Logger.getLogger(SiteMapXMLController.class);
	
	@Autowired
	private ESConnection esConnection;
	
	@Autowired
	private ElasticsearchRestTemplate template;
	
	private DefaultSitemapIndexGenerator siteMapIndexGenerator;
	
	@PostConstruct
	public void postInit() {
		// reload();
	}

	public void reload() {
		siteMapIndexGenerator = new DefaultSitemapIndexGenerator(configuration).generate();
	}
	
    @RequestMapping( value="/sitemap-{categoryIdentifier:[a-z-]+}.xml",  method = RequestMethod.GET , produces = MediaType.APPLICATION_XML_VALUE )
    public Object productList(@PathVariable String categoryIdentifier, HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) {
    	return new ModelAndView("forward:/404");
    }
	
	
    @RequestMapping( value={"sitemap.xml","sitemap-hu.xml"},   method = RequestMethod.GET , produces = MediaType.APPLICATION_XML_VALUE )
    public SiteMapIndexView main(HttpServletRequest request, HttpServletResponse response,  SessionStatus status, Model model) {
    	return new SiteMapIndexView(siteMapIndexGenerator);
    }
    
    public DefaultSitemapIndexGenerator getSiteMapIndexGenerator() {
		return siteMapIndexGenerator;
	}
	

}
