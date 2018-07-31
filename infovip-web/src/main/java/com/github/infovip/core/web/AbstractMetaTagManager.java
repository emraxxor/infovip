package com.github.infovip.core.web;

import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractMetaTagManager<DATA extends MetaTagData> extends AbstractMetaTag<DATA, ModelAndView> {

	public AbstractMetaTagManager(ModelAndView view, DATA data) {
		super(data,view);
	}

}