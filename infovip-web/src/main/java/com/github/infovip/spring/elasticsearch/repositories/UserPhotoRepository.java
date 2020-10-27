package com.github.infovip.spring.elasticsearch.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.github.infovip.core.model.UserPhotoElement;


public interface UserPhotoRepository extends ElasticsearchRepository<UserPhotoElement, String> {

}
