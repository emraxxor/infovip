package com.github.infovip.core.data.type;

/**
 * 
 * @author Attila barna
 *
 * @param <CONTEXT>
 */
public interface ApplicationThread<CONTEXT> extends Runnable {

	/**
	 * It is invoked first time
	 * The context and thread manager objects have been available
	 */
	public void postConstruct();
	
	/**
	 * It is invoked second time
	 * The context and thread manager objects have been available
	 */
	public void beforeStart();
	
	/**
	 * It is invoked third time
	 * The context and thread manager objects have been available
	 */
	public void start();
	
	/**
	 * It is invoked before the thread is going to be destroyed
	 */
	public void beforeRemove();

	/**
	 * The thread manager of the application must be set before any other method is invoked
	 * @param atm
	 */
	public void setApplicationThreadManager(ApplicationThreadManager<CONTEXT> atm);
	
	/**
	 * The context of the application must be set before any other method is invoked
	 * @param context
	 */
	public void setContext(CONTEXT context);
}
