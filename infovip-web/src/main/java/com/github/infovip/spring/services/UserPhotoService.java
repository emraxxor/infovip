package com.github.infovip.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.infovip.core.model.UserPhotoElement;
import com.github.infovip.spring.elasticsearch.repositories.UserPhotoRepository;
import com.google.common.collect.Lists;

/**
 * 
 * @author Attila Barna
 *
 */
@Service
public class UserPhotoService  {

	@Autowired
	private UserPhotoRepository repository;
	
	public UserPhotoElement save(UserPhotoElement e) {
		return this.repository.save(e);
	}
	
	public Optional<UserPhotoElement> findById(String id) {
		return this.repository.findById(id);
	}
	
	public List<UserPhotoElement> findAll() {
		return Lists.newArrayList( this.repository.findAll() );
	}
	
	public void delete(UserPhotoElement e) {
		this.repository.delete(e);
	}
	
}
