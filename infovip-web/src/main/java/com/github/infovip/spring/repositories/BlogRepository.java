package com.github.infovip.spring.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.infovip.entities.User;
import com.github.infovip.entities.UserBlog;

public interface BlogRepository extends JpaRepository<UserBlog, Long> {

	List<UserBlog> findAllByUser(User uid, Pageable pageable);
}
