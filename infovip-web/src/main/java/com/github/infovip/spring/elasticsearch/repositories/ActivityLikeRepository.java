package com.github.infovip.spring.elasticsearch.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.github.infovip.spring.elasticsearch.entities.ActivityLikeEntity;


/**
 * 
 * @author Attila Barna
 *
 */
public interface ActivityLikeRepository extends ElasticsearchRepository<ActivityLikeEntity, String> {
	
	List<ActivityLikeEntity> findByDocAndUid(String doc, Long uid);
	
	long countByDoc(String doc);
	
	List<ActivityLikeEntity> findByDoc(String doc);
}
