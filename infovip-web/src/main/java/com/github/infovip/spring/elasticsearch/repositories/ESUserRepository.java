package com.github.infovip.spring.elasticsearch.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.github.infovip.spring.elasticsearch.entities.UserEntity;

public interface ESUserRepository extends ElasticsearchRepository<UserEntity, String> {

	List<UserEntity> findByUserId(Long id);
}
