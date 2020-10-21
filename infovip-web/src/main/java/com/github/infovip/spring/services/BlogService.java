package com.github.infovip.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.infovip.entities.User;
import com.github.infovip.entities.UserBlog;
import com.github.infovip.services.UserBlogService;
import com.github.infovip.spring.repositories.BlogRepository;
import com.github.infovip.spring.repositories.UserRepository;

@Service
public class BlogService implements UserBlogService {

	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserBlog save(UserBlog e) {
		return blogRepository.save(e);
	}
	
	public Optional<UserBlog> findById(Long id) {
		return blogRepository.findById(id);
	}
	
	public List<UserBlog> findAllByUser(Long uid, Pageable pageable) {
		return blogRepository.findAllByUser( userRepository.findById(uid).get() , pageable);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserBlog createBlog(Long userId, String name) {
    	Optional<User> ub = userRepository.findById(userId);
    	
    	if ( ub.isPresent() ) 
    		return save(new UserBlog(ub.get() , name));
    	
    	throw new NullPointerException();
    }

	
	
}
