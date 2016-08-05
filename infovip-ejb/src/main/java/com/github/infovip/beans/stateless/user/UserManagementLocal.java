/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.beans.stateless.user;

import com.github.infovip.entities.User;
import com.github.infovip.spring.repositories.UserRepository;
import com.github.infovip.spring.services.UserService;
import java.util.List;
import javax.ejb.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author attila
 */
@Local
public interface UserManagementLocal {

    /**
     * Tries to find the user by the following parameters: userName and password
     * If the result is empty then null will be returned
     *
     * @param userName
     * @param password
     * @return
     */
    User findUser(String userName, String password);

    /**
     * Gets the given user by the specified id
     *
     * @param id
     * @return
     */
    public User getUser(long id);

    /**
     * Gets a list of the users
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<User> users(int offset, int limit);

    /**
     * Creates a new User
     *
     * @param u
     * @return
     */
    public User createNewUser(User u);

    /**
     * Gets the user by userName and password
     *
     * @param userName
     * @param password
     * @return
     */
    public User userByNameAndPassword(String userName, String password);

    /**
     * Modifies the given userName
     *
     * @param userName
     * @param newUserName
     */
    public void modifyUserName(String userName, String newUserName);

    /**
     * Finds the user by its email
     *
     * @param mail
     * @return
     */
    public User findUserByEmail(String mail);

    /**
     *
     * @param uname
     * @return
     */
    public User findUserByName(String uname);

    /**
     * Find all users. The query can be limited and sorted by using the
     * "pageable".
     *
     * @param pageable
     * @return
     */
    public Page<User> findUsers(Pageable pageable);

    /**
     * Validates the given entity
     *
     * @param u
     * @return
     */
    public boolean validate(User u);

    /**
     * Persists the entity. Actually this method can only be used if the
     * transaction management is set to container managed transaction
     *
     * @param entity
     * @return 
     */
    public User createEntity(User entity);

    /**
     * Merges the given entity. Actually this method can only be used if the
     * transaction management is set to container managed transaction
     *
     * @param entity
     * @return 
     */
    public User mergeEntity(User entity);

    /**
     * Removes the given entity. Actually this method can only be used if the
     * transaction management is set to container managed transaction
     *
     * @param entity
     * @return 
     */
    public User removeEntity(User entity);

    /**
     * Finds the entityClass using primary key It can only be used if there is a
     * primary key defined in the bean
     *
     * @param id
     * @return
     */
    public User find(Long id);

    /**
     * Removes the user by its id
     *
     * @param uid
     * @return
     */
    public List<User> removeByUserId(Long uid);

    /**
     * Gets the current userService
     *
     * @return
     */
    public UserService getUserService();
}
