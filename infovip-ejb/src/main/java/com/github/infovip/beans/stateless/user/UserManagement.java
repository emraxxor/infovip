/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.beans.stateless.user;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.github.infovip.entities.User;
import com.github.infovip.spring.services.UserService;
import com.github.infovip.util.BasicUtilities;

/**
 * Actually, it is used for typical user operations such as :
 * <br>
 * - Adding new user
 * <br>
 * - Removing user from the database
 * <br>
 * - Modifying user
 * <br>
 * - Authenticating user ( only checks if the user exists in the database or not
 * )
 * <br>
 * UserManagement is inherited from DefaultEntityManager class where a
 * CriteriaBuilder is set. Basically, the UserManagement bean is used for
 * registration and authentication but it would be very useful for simple
 * transactions.
 * <p>
 * It can be divided into two separate parts. One of the part is responsible for
 * the complex queries and the other is created by the repository manager.
 *
 * <h2>Notice:</h2>
 * Please notice that the transaction management is set to bean, so don't forget
 * to manage the life cycle of of transactions states!
 *
 * <h2>About managing users></h2>
 * - The user name can only be modified if it does not exists
 * <br/>
 * - Deleting the user is not possible, instead use the user_status marker (
 * user can only be removed by manually ) -
 * <br/>
 * - If an user is created the remote IP will be stored as well
 * <br/>
 *
 * @author attila
 */
@Stateless
@Local(UserManagementLocal.class)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@EnableSpringDataWebSupport
public class UserManagement implements UserManagementLocal {

    @PersistenceContext
    protected EntityManager em;


    /**
     * Repository for managing users
     */
    @Autowired
    private UserService userService;

    /**
     * Default application context
     */
    @Autowired
    private ApplicationContext context;

    /**
     * Helps to create Criteria based queries
     */
    protected CriteriaBuilder cb;

    
    
    public UserManagement() {
    }


    
    @PostConstruct
    public void postConstruct() {
        cb = getEntityManager().getCriteriaBuilder();
    }

    
    /**
     * Gets a list of the stored users
     *
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<User> users(int offset, int limit) {
        CriteriaQuery cq = cb.createQuery();
        cq.select(cq.from(User.class));
        try {
            return em.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Persists a new user
     *
     * @param u
     *
     * @return Returns with the User object if there was no exception, otherwise
     * returns with null
     *
     */
    @Override
    public User createNewUser(User u) {
    	u = em.merge(u);
    	em.persist(u);
    	return u;
    }

    /**
     * It is needed for the authorization process that finds the user by account
     * name and password.
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
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
     * Gets the user object by the mail address.
     *
     * @param mail
     * @return
     */
    @Override
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
     * Modifies the given user's name to the newUserName
     *
     * @param userName
     * @param newUserName
     */
    @Override
    public void modifyUserName(String userName, String newUserName) {
        User u = findUserByName(userName);
        u.setUserName(newUserName);
        em.merge(u);
    }

    /**
     * Gets the User by its name.
     *
     *
     * @param name
     * @return If the user doesn't exists then null will be returned
     */
    @Override
    public User findUserByName(String name) {
        CriteriaQuery cq = cb.createQuery();
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
    @Override
    public User userByNameAndPassword(String userName, String password) {
        CriteriaQuery cq = cb.createQuery();
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
    @Override
    public User getUser(long id) {
        CriteriaQuery cq = cb.createQuery();
        Root<User> u = cq.from(User.class);
        cq.select(u).where(cb.equal(u.get("userId"), id));
        try {
            return (User) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    /**
     * Find all users. The query can be limited and sorted by using the
     * "pageable".
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<User> findUsers(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @Override
    public List<User> removeByUserId(Long uid) {
        return userService.removeUserById(uid);
    }

    @Override
    public User removeEntity(User entity) {
        em.remove(entity);
        return entity;
    }

    @Override
    public User mergeEntity(User entity) {
    	em.merge(entity);
    	return entity;
    }

    @Override
    public User createEntity(User entity) {
    	em.persist(entity);
    	em.clear();
    	return entity;
    }
    
    @Override
    public boolean validate(User u) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(u);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<User>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<User> cv = iterator.next();
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "The following bean is not valid : ");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, (cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage()));
            }
            return false;
        }
        return true;
    }

    /**
     * Gets the current userService
     *
     * @return
     */
    public UserService getUserService() {
        return userService;
    }

    /*
     * Finds the entity using primary key It can only be used if there is a
     * primary key defined in the bean
     *
     * @param id
     * @return
     */
    @Override
    public User find(Long id) {
        return em.find(User.class, id);
    }
    
    @Override
    public EntityManager getEntityManager() {
    	return em;
    }

}
