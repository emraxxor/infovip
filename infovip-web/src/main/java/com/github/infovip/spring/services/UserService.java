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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.infovip.core.elasticsearch.ESContainerInterface;
import com.github.infovip.core.elasticsearch.ESExtendedDataElement;
import com.github.infovip.core.web.types.FormElement;
import com.github.infovip.entities.User;
import com.github.infovip.services.interfaces.UserServiceInterface;
import com.github.infovip.spring.elasticsearch.entities.UserEntity;
import com.github.infovip.spring.elasticsearch.repositories.ESUserRepository;
import com.github.infovip.spring.repositories.UserRepository;
import com.github.infovip.util.BasicUtilities;


/**
 *
 * @author attila
 */
@Service
@Transactional
public class UserService implements UserServiceInterface<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JpaContext jpaContext;
    
    @Autowired
    private ESUserRepository esUserRepository;

    @PersistenceContext(unitName = "infovipPU")
    private EntityManager em;
    
    
    protected CriteriaBuilder cb;
    
	@Autowired
	private ESContainerInterface<ESExtendedDataElement<?>> esContainer;
	
	@Autowired
	private RestHighLevelClient restHighLevelClient;
	
    public UserService() { }
    
    

    
    @PostConstruct
    public void postConstruct() {
        cb = em.getCriteriaBuilder();
    }

    
    /**
     * Gets a list of the stored users
     *
     * @param offset
     * @param limit
     * @return
     */
    @Transactional(readOnly = true)
    public List<User> users(int offset, int limit) {
        CriteriaQuery<User> cq = em.getCriteriaBuilder().createQuery(User.class);
        cq.select(cq.from(User.class));
        try {
            return em.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    
    /**
     * It is needed for the authorization process that finds the user by account
     * name and password.
     *
     * @param userName
     * @param password
     * @return
     */
    public User findUser(String userName, String password) {
        try {
            return (User) em.createNamedQuery("User.findByNameAndUpassword")
                    .setParameter("userName", userName)
                    .setParameter("userPassword", BasicUtilities.getMD5(password))
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    
    /**
     * It is needed for the authorization process that finds the user by account
     * name and password.
     *
     * @param userName
     * @param password
     * @return
     */
    @Transactional(readOnly = true)
    public User findUserByEmail(String userEmail, String password) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> selectFrom = query.from(User.class);
		query
			.select(selectFrom)
			.where(
					cb.and(
							cb.equal(selectFrom.get("userMail"), userEmail),
							cb.equal(selectFrom.get("userPassword"), BasicUtilities.getMD5(password))
					) 
			)
			;
		
		List<User> users = em.createQuery(query).getResultList();
		
		if ( users.size() == 1 ) 
			return users.get(0);
		
		
		return null;
    }


    /**
     * Gets the user object by the mail address.
     *
     * @param mail
     * @return
     */
    @Transactional(readOnly = true)
    public User findUserByEmail(String mail) {
        try {
            return (User) em.createNamedQuery("User.findByUmail")
                    .setParameter("userMail", mail)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    
    /**
     * Gets the User by its name.
     *
     *
     * @param name
     * @return If the user doesn't exists then null will be returned
     */
    public User findUserByName(String name) {
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> u = cq.from(User.class);
        cq.select(u).where(cb.equal(u.get("userName"), name));
        try {
            return (User) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Gets the User by its name and password
     *
     * @param userName
     * @param password
     * @return
     */
    public User userByNameAndPassword(String userName, String password) {
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> u = cq.from(User.class);
        cq.select(u).where(new Predicate[]{
            cb.equal(u.get("userName"), userName),
            cb.equal(u.get("userPassword"), password)
        });
        try {
            return (User) em.createQuery(cq).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Gets the User by its ID.
     *
     * @param id
     * @return If the user doesn't exists then null will be returned
     */
    public User getUser(long id) {
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> u = cq.from(User.class);
        cq.select(u).where(cb.equal(u.get("userId"), id));
        try {
            return (User) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }



    /**
     * Modifies the given user's name to the newUserName
     *
     * @param userName
     * @param newUserName
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyUserName(String userName, String newUserName) {
        User u = findUserByName(userName);
        u.setUserName(newUserName);
        em.merge(u);
    }
    
    /**
     * Removes an user by the given id
     * @param uid
     * @return 
     */
    public List<User> removeUserById(Long uid) {
        return userRepository.removeByUserId(uid);
    }


    /**
     * Saves the given entity into the repository
     * @param u
     * @return 
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User save(User u) {
    	u = userRepository.save(u);
    	List<UserEntity> r = esUserRepository.findByUserId(u.getUserId());
    	if ( r.size() == 0 ) {
    		esUserRepository.save( FormElement.convertTo(u, UserEntity.class) );
    	} else if ( r.size() == 1 ){
    		UserEntity ue = r.get(0);
    		FormElement.update(u, ue);
    		esUserRepository.save(ue);
    	} else {
    		throw new RuntimeException("The save process could not be completed successfully.");
    	}
    	return  u;
    	
    }

    /**
     * Deletes an entity from the repository
     * @param u 
     */
    public void delete(User u) {
        userRepository.delete(u);
    }

    /**
     * Find all users. The query can be limited and sorted by using the
     * "pageable".
     *
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<User> findUsers(Pageable pageable) {
        return findAll(pageable);
    }

    
    public User findById(Long id) {
    	return userRepository.findById(id).get();
    }
    
    /**
     * Finds users from the repository
     *
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
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
