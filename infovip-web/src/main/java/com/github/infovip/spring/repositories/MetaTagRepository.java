package com.github.infovip.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MetaTagRepository<T> extends CrudRepository<T, Long> {

}
