/*
 * Copyright (C) 2016 attila
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.infovip.spring.services;

import com.github.infovip.entities.User;
import com.github.infovip.spring.repositories.UserRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author attila
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JpaContext jpaContext;

    @PersistenceContext
    private EntityManager em;


    /**
     * Removes an user by the given id
     * @param uid
     * @return 
     */
    public List<User> removeUserById(Long uid) {
        return userRepository.removeByUserId(uid);
    }

    /**
     * Gets the current repository
     * @return 
     */
    protected UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * Saves the given entity into the repository
     * @param u
     * @return 
     */
    @Transactional
    public User save(User u) {
        return userRepository.save(u);
    }

    /**
     * Deletes an entity from the repository
     * @param u 
     */
    @Transactional
    public void delete(User u) {
        userRepository.delete(u);
    }

    /**
     * Finds users from the repository
     *
     * @param pageable
     * @return
     */
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Finds the user by the given userName
     *
     * @param userName
     * @return
     */
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * Removes everything from the repository
     */
    @Transactional
    public void deleteAll() {
        userRepository.deleteAll();
    }

    /**
     * Detaches the given entity
     * @param entity 
     */
    public void detach(User entity) {
        jpaContext.getEntityManagerByManagedType(entity.getClass()).detach(entity);
    }
}
