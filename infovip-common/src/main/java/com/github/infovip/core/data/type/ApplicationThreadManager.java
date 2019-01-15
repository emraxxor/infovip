package com.github.infovip.core.data.type;

import java.util.List;

/**
 * 
 * @author Attila Barna
 *
 * @param <CONTEXT>
 */
public interface ApplicationThreadManager<CONTEXT> {

	public void addThread(ApplicationThread<CONTEXT> thread);
	
	public void removeThread(ApplicationThread<CONTEXT> thread);
	
	public int size();
	
	public boolean isThereFreePlace();
	
	public List<ApplicationThread<CONTEXT>> threads();
}
