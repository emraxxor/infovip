package com.github.infovip.core.web.response.datatable;

import org.springframework.data.domain.Pageable;

public class DataTableManager {

	IDataTable dataTable;
	
	
	public DataTableManager(IDataTable dataTable) {
		this.dataTable = dataTable;
		dataTable.postInit();
		dataTable.beforeResult();
	}
	
	public static DataTableManager create(IDataTable dataTable) {
		return new DataTableManager(dataTable);
	}
	
	public DataTableManager pageable(Pageable pageable) {
		this.dataTable.pageable(pageable);
		return this;
	}
	
	public DataTableResult result() {
		return this.dataTable.result();
	}
	
}
