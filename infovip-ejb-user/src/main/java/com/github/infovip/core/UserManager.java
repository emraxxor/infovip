package com.github.infovip.core;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.infovip.entities.User;

@Stateless
@LocalBean
public class UserManager {

    @PersistenceContext
    protected EntityManager em;

    
	public User findById(Long id) {
		return em.find(User.class, id);
	}
}
