package com.github.infovip.core.web.response.datatable;

import java.util.ArrayList;
import java.util.List;

public class DataTableResult {

	protected String sEcho;
	
	protected Integer iTotalRecords;
	
	protected Integer iTotalDisplayRecords;
	
	protected List<Object> aaData;
	
	public DataTableResult() {
		sEcho = "undefined";
		iTotalRecords = 0;
		iTotalDisplayRecords = 0;
		this.aaData = new ArrayList<Object>();
	}

	/**
	 * @return the sEcho
	 */
	public String getsEcho() {
		return sEcho;
	}

	/**
	 * @param sEcho the sEcho to set
	 */
	public DataTableResult setsEcho(String sEcho) {
		this.sEcho = sEcho;
		return this;
	}

	/**
	 * @return the iTotalRecords
	 */
	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	/**
	 * @param iTotalRecords the iTotalRecords to set
	 */
	public DataTableResult setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
		return this;
	}

	/**
	 * @return the iTotalDisplayRecords
	 */
	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	/**
	 * @param iTotalDisplayRecords the iTotalDisplayRecords to set
	 */
	public DataTableResult setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		return this;
	}
	
	public DataTableResult addElement(Object element) {
		this.aaData.add(element);
		return this;
	}

	/**
	 * @return the aaData
	 */
	public List<?> getAaData() {
		return aaData;
	}

	/**
	 * @param aaData the aaData to set
	 */
	public DataTableResult setAaData(List<Object> aaData) {
		this.aaData = aaData;
		return this;
	}
	
	
	
}
