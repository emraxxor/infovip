package com.github.infovip.web.application.es.activity;

import com.github.infovip.core.scroll.AbstractScrollContentEvent;

/**
 * 
 * @author Attila Barna
 *
 */
public class ActivityLikeResponse extends AbstractScrollContentEvent<ActivityLikeElement,ActivityResponseElement> {

	public ActivityLikeResponse(ActivityResponseElement element) {
		super(element);
	}

	@Override
	public void beforeProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProcess(ActivityLikeElement element) {
		
		
	}

	@Override
	public void afterProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ActivityResponseElement getResponseElement() {
		return responseElement;
	}

}
