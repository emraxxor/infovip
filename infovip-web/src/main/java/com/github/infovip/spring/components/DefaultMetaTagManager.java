package com.github.infovip.spring.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.spring.services.MetatagService;

/**
 * 
 * @author Attila Barna
 *
 * @package infovip.metatag.manager
 */
@Component
public class DefaultMetaTagManager<T extends MetatagService<T>> {

    @Autowired
    private MetatagService metatagService;

    
    public void metaData(String requestURI, ModelAndView view) {
    	throw new RuntimeException("Not implemented yet");
    }

}
