/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.infovip.db;

import com.github.infovip.entities.User;
import java.util.List;
import javax.ejb.Local;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

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
     * Gets the given user by its name
     *
     * @param name
     * @return
     */
    public User getUser(String name);

}
