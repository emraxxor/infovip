package com.github.infovip.spring.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.github.infovip.core.web.AppicationMetatagManager;
import com.github.infovip.entities.CfgApplicationMetaData;
import com.github.infovip.spring.services.MetatagService;

/**
 * 
 * @author Attila Barna
 *
 * @package infovip.metatag.manager
 */
@Component
public class DefaultMetaTagManager {

    @Autowired
    private MetatagService<CfgApplicationMetaData> metatagService;
    
    public void metaData(String requestURI, ModelAndView view) {
    	CfgApplicationMetaData metaContent = metatagService.findByContentType(requestURI);
		if ( metaContent != null && metaContent.getId() != null ) {
			AppicationMetatagManager<CfgApplicationMetaData> amm = new AppicationMetatagManager<CfgApplicationMetaData>(view, metaContent);
			amm.process();
		}
    }

}
