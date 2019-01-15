package com.github.infovip.core.web.sitemap;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

public class SiteMapIndexView extends AbstractView  {

	private DefaultSitemapIndexGenerator sitemap;
	
	public SiteMapIndexView(DefaultSitemapIndexGenerator o) {
		this.sitemap = o;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType(MediaType.APPLICATION_XML_VALUE); 
		try (Writer writer = response.getWriter()) {
			writer.append(sitemap.toString()); 
		}
	}

}
