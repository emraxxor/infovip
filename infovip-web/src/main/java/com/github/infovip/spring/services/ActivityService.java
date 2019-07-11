package com.github.infovip.spring.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.infovip.spring.elasticsearch.entities.ActivityLikeEntity;
import com.github.infovip.spring.elasticsearch.repositories.ActivityLikeRepository;

/**
 * 
 * @author Attila Barna
 *
 */
@Service
public class ActivityService implements Serializable {

	@Autowired
	private ActivityLikeRepository repository;
	
	public ActivityService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<ActivityLikeEntity> find(String doc, Long uid) {
		return repository.findByDocAndUid(doc, uid);
	}
	
	public long countByDoc(String doc) {
		return repository.countByDoc(doc);
	}
	
	public List<ActivityLikeEntity> findByDoc(String doc) {
		return repository.findByDoc(doc);
	}

	public void delete(ActivityLikeEntity e) {
		repository.delete(e);
	}

	/*
    * Saves the current entity
    * @param e
    * @return
    */
   public ActivityLikeEntity save(ActivityLikeEntity e) {
       repository.save(e);
       return e;
   }

}
