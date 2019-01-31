package com.github.infovip.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.infovip.entities.CfgApplicationMetaData;

@Repository
@Transactional
public interface MetaTagRepository<T extends CfgApplicationMetaData> extends CrudRepository<T, Long> {

	public T findByMetaUrl(String url);
}
