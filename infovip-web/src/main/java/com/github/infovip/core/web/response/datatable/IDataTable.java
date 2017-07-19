package com.github.infovip.core.web.response.datatable;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDataTable {

	public void formatter(Page<?> data);
	
	public void formatter(List<?> data);

	public void beforeResult();
	
	public void postInit();
	
	public void initQuery(CriteriaQuery<?> cq,CriteriaBuilder cb);
	
	public DataTableResult result();

	public int offset();
	
	public int limit();
	
	public IDataTable pageable(Pageable pageable);
	
	
}
