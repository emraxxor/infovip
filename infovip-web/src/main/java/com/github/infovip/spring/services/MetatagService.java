package com.github.infovip.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.infovip.spring.repositories.MetaTagRepository;


@Service
@Transactional
public class MetatagService<T> {

	@Autowired
	private MetaTagRepository<T> metaTagRepository;
	
	public T findByContentType(String contentType) {
		return null;
	}

}
