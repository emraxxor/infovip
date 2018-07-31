package com.github.infovip.core.web;

import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractMetaTag<DATA,MODEL extends ModelAndView> implements MetaTag {

	protected DATA data;
	
	protected MODEL modelAndView;
	
	public AbstractMetaTag(DATA d , MODEL m) {
		this.data = d;
		this.modelAndView = m;
	}
	
	public abstract void process();
}
