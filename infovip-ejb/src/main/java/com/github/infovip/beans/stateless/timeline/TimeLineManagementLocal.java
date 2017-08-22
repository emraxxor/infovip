package com.github.infovip.beans.stateless.timeline;

import javax.ejb.Local;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.infovip.spring.elasticsearch.entities.TimelinePostEntity;

@Local
public interface TimeLineManagementLocal {

	public void delete(TimelinePostEntity e);
	
	public boolean exists(String id);
	
	public TimelinePostEntity save(TimelinePostEntity e);
	
	public Iterable<TimelinePostEntity> findAll();
	
	public Page<TimelinePostEntity> findAll(Pageable pgbl);
	
}
