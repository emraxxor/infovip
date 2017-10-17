package com.github.infovip.core.web.webix;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataTableResponse  {

	protected List<Map<String,Object>> data;
	
	protected int pos;
	
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
	
	public List<Map<String,Object>> getData() {
		return data;
	}
	
	public void setData(List<Map<String,Object>> data) {
		this.data = data;
	}
	
	public void setParams(List<Object> params) {
		this.params = params;
	}
}
