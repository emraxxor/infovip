package com.github.infovip.test.datatable;

import com.github.infovip.core.web.response.datatable.DataTableElement;

public class TableElement implements DataTableElement {

	private String name;
	
	private String mail;
	
	public TableElement(String name,String mail) {
		this.name = name;
		this.mail = mail;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getName() {
		return name;
	}
}
