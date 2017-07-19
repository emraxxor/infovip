package com.github.infovip.test.datatable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.github.infovip.core.web.response.datatable.DefaultDataTable;
import com.github.infovip.entities.User;

public class TableTest extends DefaultDataTable {

	public TableTest(EntityManager em, HttpServletRequest request, HttpServletResponse response) {
		super(em, request, response);
	}

	@Override
	public void formatter(Page<?> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void formatter(List<?> data) {
		for(Object o : data ) {
			User u = (User) o;
			List<Object> odata = new ArrayList<>();
			odata.add(u.getUserName());
			odata.add(u.getUserMail());
			odata.add(u.getLogRegistration().getIp());
			result.addElement(odata);
		}
		result.setsEcho("echo");
		result.setiTotalDisplayRecords(data.size());
		result.setiTotalRecords(data.size());
	}

	@Override
	public void initQuery(CriteriaQuery cq, CriteriaBuilder cb) {
        Root<User> u = cq.from(User.class);
        cq.select(u);
	}

}
