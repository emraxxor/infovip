package com.github.infovip.beans.stateless.timeline;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity;
import com.github.infovip.spring.services.TimelineService;
import com.github.infovip.spring.services.UserService;

/**
 * Session Bean implementation class TimeLineManagement
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.BEAN)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@EnableSpringDataWebSupport
@Local(TimeLineManagementLocal.class)
public class TimeLineManagement implements TimeLineManagementLocal {
	/**
	 * Repository for managing users
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private TimelineService timeLineService;
	
    /**
     * Default constructor. 
     */
    public TimeLineManagement() {
        // TODO Auto-generated constructor stub
    }

	public void delete(TimelinePostEntity e) {
		timeLineService.delete(e);
	}

	public boolean exists(String id) {
		return timeLineService.exists(id);
	}

	public TimelinePostEntity save(TimelinePostEntity e) {
		return timeLineService.save(e);
	}

	public Iterable<TimelinePostEntity> findAll() {
		return timeLineService.findAll();
	}

	public Page<TimelinePostEntity> findAll(Pageable pgbl) {
		return timeLineService.findAll(pgbl);
	}
    
    
}
