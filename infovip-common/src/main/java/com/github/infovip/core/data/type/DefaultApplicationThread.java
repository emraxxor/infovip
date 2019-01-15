package com.github.infovip.core.data.type;

import org.apache.log4j.Logger;

public abstract class DefaultApplicationThread<CONTEXT> extends Thread implements ApplicationThread<CONTEXT> {

	protected ApplicationThreadManager<CONTEXT> atm;
	
	protected CONTEXT context;
	
	protected final Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void run() {
		this.atm.removeThread(this);
	}
	
	@Override
	public void setApplicationThreadManager(ApplicationThreadManager<CONTEXT> atm) {
		this.atm = atm;
	}

	
	@Override
	public void setContext(CONTEXT context) {
		this.context = context;
	}
	
}
