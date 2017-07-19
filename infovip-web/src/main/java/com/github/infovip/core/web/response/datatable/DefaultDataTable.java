package com.github.infovip.core.web.response.datatable;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class DefaultDataTable implements IDataTable {

	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
    private CriteriaBuilder cb;
	
	private EntityManager em;
	
	private CriteriaQuery query;
	
	protected Pageable pageable;
	
	protected DataTableResult result;
	
	public DefaultDataTable(EntityManager em, HttpServletRequest request,HttpServletResponse response) {
		this.result = new DataTableResult();
		this.request = request;
		this.response = response;
		this.em = em;
		this.cb = em.getCriteriaBuilder();
		this.query = cb.createQuery();
		
	}
	
	@Override
	public abstract void formatter(Page<?> data);
	
	public abstract void formatter(List<?> data);
	
	@Override
	public void beforeResult() {

	}
	
	public abstract void initQuery(CriteriaQuery cq, CriteriaBuilder cb);

	@Override
	public IDataTable pageable(Pageable pageable) {
		this.pageable = pageable;
		return this;
	}
	
	public DataTableResult result() {
		formatter(em.createQuery(query).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize()) .getResultList());
		return result;
	}
	
	@Override
	public int offset() {
		return  Integer.valueOf( request.getParameter("iDisplayStart") );
	}
	
	@Override
	public int limit() {
		return  Integer.valueOf( request.getParameter("iDisplayLength") );
	}

	@Override
	public void postInit() {
		this.initQuery(query, cb);
	}
	
}
