package com.github.infovip.core.web.webix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author Attila Barna
 * 
 * @package infovip.web.webix
 *
 *
 * @param <T>
 */
public class DataTableResponse<T>  {

	protected List<T> data;
	
	protected int pos;

	protected int total_count;
	
	protected List<Object> params;

	public DataTableResponse() {
		this.data = new ArrayList<>();
	}
	
	
	public DataTableResponse(int pos) {
		this();
		this.pos = pos;
	}
	
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public List<T> getData() {
		return data;
	}
	
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public void setParams(List<Object> params) {
		this.params = params;
	}
	
	public int getTotal_count() {
		return total_count;
	}
	
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
}
