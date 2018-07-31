package com.github.infovip.core.web;

import org.springframework.web.servlet.ModelAndView;

public class DefaultMetaTagManager<DATA extends MetaTagData> extends AbstractMetaTagManager<DATA> {

	public DefaultMetaTagManager(ModelAndView view, DATA data) {
		super(view, data);
	}
	
	@Override
	public void process() {
		modelAndView.addObject("metaTitle", URLSanitizer.standard(data.getTitle()) );
		modelAndView.addObject("metaDescription", URLSanitizer.standard( data.getMetaDescription() ));
		modelAndView.addObject("metaKeywords", URLSanitizer.standard( data.getMetaKeyword()));
		modelAndView.addObject("ogTitle", URLSanitizer.standard( data.getOgTitle()));
		modelAndView.addObject("ogType", URLSanitizer.standard( data.getOgType()));
		modelAndView.addObject("ogDescription", URLSanitizer.standard( data.getOgDescription()));
	}
}
