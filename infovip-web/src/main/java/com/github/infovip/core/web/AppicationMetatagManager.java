package com.github.infovip.core.web;

import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.entities.CfgApplicationMetaData;


/**
 * 
 * @author Attila Barna
 *
 * @param <T>
 */
public class AppicationMetatagManager<T extends CfgApplicationMetaData> extends AbstractMetaTag<T, ModelAndView> {

	public AppicationMetatagManager(ModelAndView view, T seo) {
		super(seo,view);
	}
	
	public void process() {
		modelAndView.addObject("metaTitle", URLSanitizer.standard(data.getTitle()) );
		modelAndView.addObject("metaDescription", URLSanitizer.standard( data.getMetaDescription() ));
		modelAndView.addObject("metaKeywords", URLSanitizer.standard( data.getMetaKeyword()));
		modelAndView.addObject("ogTitle", URLSanitizer.standard( data.getOgTitle()));
		modelAndView.addObject("ogType", URLSanitizer.standard( data.getOgType()));
		modelAndView.addObject("ogDescription", URLSanitizer.standard( data.getOgDescription()));
	}

}
