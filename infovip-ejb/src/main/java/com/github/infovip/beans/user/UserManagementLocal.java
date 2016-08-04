/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.beans.user;

import com.github.infovip.entities.User;
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

    public Page<User> findUsers(Pageable pageable);

}
