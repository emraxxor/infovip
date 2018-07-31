package com.github.infovip.core.web.webix.markers.datatable;

import java.util.List;

/**
 * 
 * @author attila
 *
 * @package infovip.web.webix
 *
 */
public interface WIXDataSourceInterface<T> {

	public int total();
	
	public String token();

	public void query();
	
	public int start();
	
	public int count();
	
	public List<T> content();

	public int size();

}
