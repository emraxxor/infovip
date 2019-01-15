package com.github.infovip.core.web.application;

public class ApplicationConfiguration {
	
	private boolean applicationIsBeingShutDown;
	
	public ApplicationConfiguration() {
		this.applicationIsBeingShutDown = false;
	}

	/**
	 * @return the applicationIsBeingShutDown
	 */
	public boolean isApplicationIsBeingShutDown() {
		return applicationIsBeingShutDown;
	}

	/**
	 * @param applicationIsBeingShutDown the applicationIsBeingShutDown to set
	 */
	public void setApplicationIsBeingShutDown(boolean applicationIsBeingShutDown) {
		this.applicationIsBeingShutDown = applicationIsBeingShutDown;
	}
	
	
}
