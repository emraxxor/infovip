package com.github.infovip.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.github.infovip.entities.UserBlog;

/**
 * 
 * @author attila
 *
 */
public interface UserBlogService {

	public Optional<UserBlog> findById(Long id) ;
	
	public List<UserBlog> findAllByUser(Long uid, Pageable pageable);
	
    public UserBlog createBlog(Long userId, String name);
    
    public UserBlog save(UserBlog e);
    
}
